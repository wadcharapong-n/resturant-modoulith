package com.north.restaurant.bill;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("bill")
@RequiredArgsConstructor
public class BillController {
    final BillService billService;

    @GetMapping("/now")
    public Double getTotalAmount() {
        return billService.getTotalPriceByDate(LocalDate.now());
    }

    @GetMapping()
    public Double getAllTotalAmount() {
        return billService.getTotalPrice();
    }
}
