package com.north.restaurant.bill;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNo;
    private LocalDateTime createDate;
    private LocalDateTime orderCreateDate;
    private Double totalPrice;

    public Bill(String orderNo, LocalDateTime orderCreateDate, Double totalPrice) {
        this.orderNo = orderNo;
        this.orderCreateDate = orderCreateDate;
        this.totalPrice = totalPrice;
    }

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
    }
}
