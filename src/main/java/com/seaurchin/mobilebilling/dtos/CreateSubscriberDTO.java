package com.seaurchin.mobilebilling.dtos;

public class CreateSubscriberDTO {
    private String phoneNumber;
    private String name;
    private boolean active;

    public CreateSubscriberDTO() {}

    public CreateSubscriberDTO(String phoneNumber, String name, boolean active) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.active = active;
    }

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
