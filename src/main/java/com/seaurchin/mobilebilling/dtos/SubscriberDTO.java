package com.seaurchin.mobilebilling.dtos;

import com.seaurchin.mobilebilling.entities.Subscriber;

public class SubscriberDTO {
    private Long id;
    private String phoneNumber;
    private String name;
    private boolean active;

    public SubscriberDTO() {}

    public SubscriberDTO(Long id, String phoneNumber, String name, boolean active) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.active = active;
    }

    public SubscriberDTO(Subscriber subscriber) {
        this.id = subscriber.getId();
        this.phoneNumber = subscriber.getPhoneNumber();
        this.name = subscriber.getName();
        this.active = subscriber.isActive();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
