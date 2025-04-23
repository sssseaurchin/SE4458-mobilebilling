package com.seaurchin.mobilebilling.services;

import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.exceptions.SubscriberNotFoundException;
import com.seaurchin.mobilebilling.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public Optional<Subscriber> findById(Long id) {
        return subscriberRepository.findById(id);
    }
    public Subscriber getSubscriberById(Long id) {
        return subscriberRepository.findById(id)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not found with ID: " + id));
    }

    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }
}
