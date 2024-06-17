package com.north.restaurant.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Item(
        @Id Long id,
        String name,
        Double price
) {
}
