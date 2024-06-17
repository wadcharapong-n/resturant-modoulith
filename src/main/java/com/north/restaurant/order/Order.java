package com.north.restaurant.order;

import com.north.restaurant.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "e_order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNo;
    private LocalDateTime createDate;
    @ManyToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> items;
    private Double totalPrice;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        orderNo = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
