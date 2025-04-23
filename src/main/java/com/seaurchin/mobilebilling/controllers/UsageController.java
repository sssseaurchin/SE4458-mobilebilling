package com.seaurchin.mobilebilling.controllers;

import com.seaurchin.mobilebilling.dtos.UsageLogDTO;
import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.entities.UsageLog;
import com.seaurchin.mobilebilling.repositories.SubscriberRepository;
import com.seaurchin.mobilebilling.repositories.UsageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usage")
public class UsageController {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private UsageLogRepository usageLogRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addUsage(@RequestBody UsageLogDTO usageDto) {
        Optional<Subscriber> subscriberOpt = subscriberRepository.findById(usageDto.getSubscriberId());

        if (subscriberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Subscriber ID " + usageDto.getSubscriberId() + " not found.");
        }


        Subscriber subscriber = subscriberOpt.get();

        UsageLog usage = new UsageLog();
        usage.setSubscriber(subscriber);

        String type = usageDto.getType();

        if (!type.equalsIgnoreCase("call") && !type.equalsIgnoreCase("internet")) {
            return ResponseEntity.badRequest().body("Invalid type: must be 'call' or 'internet'");
        }

        usage.setType(type);
        if (type.equalsIgnoreCase("call")){
            usage.setAmount(10);
        }
        else {
            usage.setAmount(1);
        }

        usage.setTimestamp(LocalDateTime.of(usageDto.getYear(), usageDto.getMonth(), 1, 0, 0));
        usage.setBilled(false);

        usageLogRepository.save(usage);

        return ResponseEntity.ok("Usage created.");
    }

    @PostMapping("/test/usage-bulk")
    public ResponseEntity<?> testAddBulkUsage(@RequestParam Long subscriberId,
                                              @RequestParam String type,
                                              @RequestParam int amount,
                                              @RequestParam int year,
                                              @RequestParam int month) {
        if (month < 1 || month > 12) {
            return ResponseEntity.badRequest().body("Invalid month: must be between 1 and 12.");
        }
        if (year < 2000 || year > 2100) {
            return ResponseEntity.badRequest().body("Invalid year: must be between 2000 and 2100.");
        }
        if (!type.equalsIgnoreCase("call") && !type.equalsIgnoreCase("internet")) {
            return ResponseEntity.badRequest().body("Invalid type: must be 'call' or 'internet'");
        }

        Optional<Subscriber> subscriberOpt = subscriberRepository.findById(subscriberId);
        if (subscriberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Subscriber not found.");
        }

        Subscriber subscriber = subscriberOpt.get();

        UsageLog usage = new UsageLog();
        usage.setSubscriber(subscriber);
        usage.setType(type);
        usage.setAmount(amount);
        usage.setTimestamp(LocalDateTime.of(year, month, 1, 0, 0));
        usage.setBilled(false);

        usageLogRepository.save(usage);

        return ResponseEntity.ok("Usage created.");
    }
//    @DeleteMapping("/debug/flush-all")
//    public ResponseEntity<?> flushDatabase() {
//        usageLogRepository.deleteAll();
//        subscriberRepository.deleteAll(); // Note: Deleting subscribers also removes dependent bills if cascaded
//        return ResponseEntity.ok("Database flushed: all subscribers and usage logs deleted.");
//    }



}
