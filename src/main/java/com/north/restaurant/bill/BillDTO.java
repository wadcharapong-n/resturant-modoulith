package com.north.restaurant.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BillDTO {
    private String orderNo;
    private LocalDateTime createDate;
    private Double totalPrice;
}
