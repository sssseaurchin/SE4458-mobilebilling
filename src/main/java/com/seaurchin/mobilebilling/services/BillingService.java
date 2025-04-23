package com.seaurchin.mobilebilling.services;

import com.seaurchin.mobilebilling.dtos.BillDetailsDTO;
import com.seaurchin.mobilebilling.entities.Bill;
import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.entities.UsageLog;
import com.seaurchin.mobilebilling.exceptions.BillNotFoundException;
import com.seaurchin.mobilebilling.repositories.BillRepository;
import com.seaurchin.mobilebilling.repositories.UsageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UsageLogRepository usageLogRepository;

    @Autowired
    private UsageService usageService;

    public Bill calculateBill(Subscriber subscriber, int year, int month) {
        List<Bill> existingBills = billRepository.findBySubscriberAndMonthAndYear(subscriber, month, year);
        if (!existingBills.isEmpty()) {
            return existingBills.get(0);
        }


        List<UsageLog> logs = usageLogRepository.findBySubscriberAndTimestampBetweenAndBilled(
                subscriber,
                LocalDateTime.of(year, month, 1, 0, 0),
                LocalDateTime.of(year, month, 1, 0, 0).plusMonths(1),
                false
        );

        int callMinutes = logs.stream()
                .filter(log -> log.getType().equalsIgnoreCase("call"))
                .mapToInt(UsageLog::getAmount)
                .sum();

        int internetMB = logs.stream()
                .filter(log -> log.getType().equalsIgnoreCase("internet"))
                .mapToInt(UsageLog::getAmount)
                .sum();

        double total = 0.0;

        if (callMinutes > 1000) {
            int extraChunks = (int) Math.ceil((callMinutes - 1000) / 1000.0);
            total += extraChunks * 10.0;
        }

        if (internetMB > 20000) {
            int extraMB = internetMB - 20000;
            int extraChunks = (int) Math.ceil(extraMB / 10000.0);
            total += 50 + (extraChunks * 10);
        } else if (internetMB > 0) {
            total += 50.0;
        }

        Bill bill = new Bill(subscriber, month, year, callMinutes, internetMB, total, false);
        billRepository.save(bill);

        logs.forEach(log -> log.setBilled(true));
        usageLogRepository.saveAll(logs);

        return bill;
    }

    public String processPayment(Bill bill, double amount) {
        bill.addPaidAmount(amount);

        if (bill.getPaidAmount() >= bill.getAmount()) {
            bill.setPaid(true);
        } else {
            bill.setPaid(false);
        }

        billRepository.save(bill);

        if (bill.isPaid()) {
            return "Payment successful. Bill marked as paid.";
        } else {
            double remaining = bill.getAmount() - bill.getPaidAmount();
            remaining = remaining < 0 ? 0 : remaining;
            return "Partial payment received. Remaining amount: " + remaining;
        }
    }



    public Bill getBillForMonth(Subscriber subscriber, int year, int month) {
        List<Bill> bills = billRepository.findBySubscriberAndMonthAndYear(subscriber, month, year);

        if (bills.isEmpty()) {
            throw new BillNotFoundException("No bill found for subscriber in " + month + "/" + year);
        }

        return bills.get(0);
    }

    public List<BillDetailsDTO> getDetailedBillsPaged(Subscriber subscriber, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bill> paged = billRepository.findBySubscriber(subscriber, pageable);

        return paged.stream()
                .map(b -> new BillDetailsDTO(
                        b.getMonth(),
                        b.getYear(),
                        b.getTotalCallMinutes(),
                        b.getTotalInternetMB(),
                        b.getAmount(),
                        b.isPaid()
                )).toList();
    }
}