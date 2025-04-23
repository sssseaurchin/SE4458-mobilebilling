package com.seaurchin.mobilebilling.repositories;

import com.seaurchin.mobilebilling.entities.UsageLog;
import com.seaurchin.mobilebilling.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    List<UsageLog> findBySubscriberAndTimestampBetween(Subscriber subscriber, LocalDateTime start, LocalDateTime end);
    List<UsageLog> findBySubscriberAndTimestampBetweenAndBilled(
            Subscriber subscriber, LocalDateTime start, LocalDateTime end, boolean billed);

}
