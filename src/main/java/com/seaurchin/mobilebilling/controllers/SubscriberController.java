package com.seaurchin.mobilebilling.controllers;

import com.seaurchin.mobilebilling.dtos.CreateSubscriberDTO;
import com.seaurchin.mobilebilling.dtos.SubscriberDTO;
import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<SubscriberDTO> create(@RequestBody CreateSubscriberDTO dto) {
        Subscriber subscriber = new Subscriber(dto.getPhoneNumber(), dto.getName(), dto.isActive());
        Subscriber saved = subscriberService.save(subscriber);
        return ResponseEntity.ok(new SubscriberDTO(saved));
    }


    @GetMapping("/test/{id}")
    public Subscriber get(@PathVariable Long id) {
        return subscriberService.findById(id).orElseThrow();
    }
}
