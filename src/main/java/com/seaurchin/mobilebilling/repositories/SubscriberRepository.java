package com.seaurchin.mobilebilling.repositories;

import com.seaurchin.mobilebilling.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByPhoneNumber(String phoneNumber);
}
