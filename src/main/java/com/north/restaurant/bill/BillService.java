package com.north.restaurant.bill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @ApplicationModuleListener
    public void createBillEvent(BillDTO event) {
        Bill bill = new Bill(event.getOrderNo(), event.getCreateDate(), event.getTotalPrice());
        billRepository.save(bill);
        log.info("receive bill {} : total {} ", bill.getOrderNo(), bill.getTotalPrice());
    }

    public Double getTotalPriceByDate(LocalDate date) {
        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = date.plusDays(1).atStartOfDay();
        List<Bill> bills = billRepository.findAllByCreateDate(dateFrom, dateTo);
        return bills.stream().mapToDouble(Bill::getTotalPrice).sum();
    }

    public Double getTotalPrice() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream().mapToDouble(Bill::getTotalPrice).sum();
    }
}
