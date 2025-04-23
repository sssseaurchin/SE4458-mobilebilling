package com.seaurchin.mobilebilling.repositories;

import com.seaurchin.mobilebilling.entities.Bill;
import com.seaurchin.mobilebilling.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBySubscriberAndMonthAndYear(Subscriber subscriber, int month, int year);
    Page<Bill> findBySubscriber(Subscriber subscriber, Pageable pageable);
    List<Bill> findBySubscriber(Subscriber subscriber);

}
