package com.seaurchin.mobilebilling.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subscriber subscriber;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "amount")
    private int amount;


    @Column(nullable = false)
    private boolean billed = false;


    public UsageLog() {
    }

    public UsageLog(Subscriber subscriber, String type, LocalDateTime timestamp) {
        this.subscriber = subscriber;
        this.type = type;
        this.timestamp = timestamp;
        billed = false;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public boolean isBilled() {
        return billed;
    }

    public void setBilled(boolean billed) {
        this.billed = billed;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
