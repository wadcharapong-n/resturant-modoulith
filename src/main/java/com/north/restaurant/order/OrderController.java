package com.north.restaurant.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public void createOrder(
            @RequestBody Order order
    ) {
        orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void cancelOrder(
            @PathVariable Long id
    ) {
        orderService.cancelOrder(id);
    }
}
