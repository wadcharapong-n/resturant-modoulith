package com.north.restaurant.order;

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
    @ManyToMany
    private List<Item> items;
    private Double totalPrice;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        orderNo = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}