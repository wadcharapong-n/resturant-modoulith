package com.north.restaurant.order;

import com.north.restaurant.bill.BillDTO;
import com.north.restaurant.notification.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    final OrderRepository orderRepository;
    private final ApplicationEventPublisher events;

    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher events) {
        this.orderRepository = orderRepository;
        this.events = events;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
        events.publishEvent(new BillDTO(order.getOrderNo(), order.getCreateDate(), order.getTotalPrice()));
        events.publishEvent(new NotificationDTO("create order " + order.getOrderNo(), "order price : " + order.getTotalPrice()));
        log.info("Save order : {}", order);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Order cancelOrder = new Order();
        BeanUtils.copyProperties(order, cancelOrder);
        cancelOrder.setTotalPrice(order.getTotalPrice() * (-1));
        orderRepository.save(cancelOrder);
        events.publishEvent(new BillDTO(order.getOrderNo(), order.getCreateDate(), order.getTotalPrice()));
        events.publishEvent(new NotificationDTO("cancel order" + order.getOrderNo(), "order price : " + order.getTotalPrice()));
        log.info("Cancel order : {}", order);
    }
}
