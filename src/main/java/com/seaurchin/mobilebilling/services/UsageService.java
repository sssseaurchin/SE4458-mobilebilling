package com.seaurchin.mobilebilling.services;

import com.seaurchin.mobilebilling.entities.Subscriber;
import com.seaurchin.mobilebilling.entities.UsageLog;
import com.seaurchin.mobilebilling.repositories.UsageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsageService {

    @Autowired
    private UsageLogRepository usageLogRepository;

    public List<UsageLog> getUsageForMonth(Subscriber subscriber, int year, int month) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1);
        return usageLogRepository.findBySubscriberAndTimestampBetween(subscriber, start, end);
    }
}
