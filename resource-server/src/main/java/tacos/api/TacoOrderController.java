package tacos.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;
import tacos.domain.TacoUser;
import tacos.web.OrderProps;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public record TacoOrderController(OrderRepository orderRepository, OrderProps orderProps) {

    private static final Logger LOG = LoggerFactory.getLogger(TacoOrderController.class);

    @GetMapping("/deliveryZip/{deliveryZip}")
    public List<TacoOrder> findOrdersByDeliveryZip(@PathVariable String deliveryZip) {
        return orderRepository.findByDeliveryZip(deliveryZip);
    }

    @GetMapping("/deliveryZip/{deliveryZip}/startDate/{startDate}/endDate/{endDate}")
    public List<TacoOrder> findOrdersByDeliveryZipAndDate(@PathVariable String deliveryZip,
                                                          @PathVariable Date startDate,
                                                          @PathVariable Date endDate) {
        return orderRepository.readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip, startDate, endDate);
    }

    @GetMapping
    public List<TacoOrder> findOrdersByUser(@AuthenticationPrincipal TacoUser user) {

        LOG.info("page size: {}", orderProps.getPageSize());
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<TacoOrder> fetchOrder(@PathVariable Long orderId) {
        Optional<TacoOrder> optOrder = orderRepository.findById(orderId);

        return optOrder.map(tacoOrder -> ResponseEntity.ok().body(tacoOrder))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TacoOrder putOrder(@PathVariable Long orderId, @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TacoOrder patchOrder(@PathVariable Long orderId, @RequestBody TacoOrder patch) {

        TacoOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order with id " + orderId + " exists."));

        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }

        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
