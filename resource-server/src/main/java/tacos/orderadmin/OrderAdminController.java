package tacos.orderadmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/admin")
public class OrderAdminController {

    private final OrderAdminService adminService;

    public OrderAdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String listOrders(Model model) {
        Iterable<TacoOrder> tacoOrders = adminService.listOrders();
        log.info("orders: " + tacoOrders);
        model.addAttribute("orders", tacoOrders);
        return "admin";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        log.info("delete all orders.");
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }

    @GetMapping("/order/{id}")
    public TacoOrder getOrder(@PathVariable Long id) {
        return adminService.getOrder(id);
    }
}
