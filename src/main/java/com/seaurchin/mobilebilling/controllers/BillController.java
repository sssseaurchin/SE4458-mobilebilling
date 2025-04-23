package com.seaurchin.mobilebilling.controllers;

import com.seaurchin.mobilebilling.dtos.BillDTO;
import com.seaurchin.mobilebilling.dtos.BillDetailsDTO;
import com.seaurchin.mobilebilling.dtos.BillSummaryDTO;
import com.seaurchin.mobilebilling.entities.Bill;
import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.services.BillingService;
import com.seaurchin.mobilebilling.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
public class BillController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping("/calculate")
    public Bill calculateBill(@RequestBody BillDTO dto) {
        Subscriber s = subscriberService.findById(dto.getSubscriberId()).orElseThrow();
        return billingService.calculateBill(s, dto.getYear(), dto.getMonth());
    }

    @PostMapping("/pay")
    public String payBill(
            @RequestParam Long subscriberId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam double amount) {

        Subscriber s = subscriberService.findById(subscriberId).orElseThrow();
        Bill bill = billingService.getBillForMonth(s, year, month);

        return billingService.processPayment(bill, amount);
    }

    @GetMapping("/query/{subscriberId}/{year}/{month}")
    public ResponseEntity<BillSummaryDTO> getBillSummary(
            @PathVariable Long subscriberId,
            @PathVariable int year,
            @PathVariable int month) {
        Subscriber subscriber = subscriberService.getSubscriberById(subscriberId);
        Bill bill = billingService.getBillForMonth(subscriber, year, month);
        return ResponseEntity.ok(new BillSummaryDTO(bill.getAmount(), bill.isPaid()));
    }

    @GetMapping("/query/detailed/{subscriberId}")
    public List<BillDetailsDTO> getDetailedBills(
            @PathVariable Long subscriberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Subscriber s = subscriberService.findById(subscriberId).orElseThrow();
        return billingService.getDetailedBillsPaged(s, page, size);
    }
}