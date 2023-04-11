package tacos.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;
import tacos.domain.TacoUser;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public record OrderController(OrderRepository orderRepository, OrderProps props) {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal TacoUser user,
                            @ModelAttribute TacoOrder tacoOrder) {
        if (tacoOrder.getDeliveryName() == null) {
            tacoOrder.setDeliveryName(user.getFullname());
        }
        if (tacoOrder.getDeliveryStreet() == null) {
            tacoOrder.setDeliveryStreet(user.getStreet());
        }
        if (tacoOrder.getDeliveryCity() == null) {
            tacoOrder.setDeliveryCity(user.getCity());
        }
        if (tacoOrder.getDeliveryState() == null) {
            tacoOrder.setDeliveryState(user.getState());
        }
        if (tacoOrder.getDeliveryZip() == null) {
            tacoOrder.setDeliveryZip(user.getZip());
        }

        tacoOrder.setCcNumber("38520000023237");

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal TacoUser user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        LOG.info("process order, order user {}", user);
        order.setUser(user);

        orderRepository.save(order);

        LOG.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal TacoUser user, Model model) {

        LOG.info("page size: " + props.getPageSize());

        Pageable pageable = PageRequest.of(0, props.getPageSize());

        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
