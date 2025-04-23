package com.seaurchin.mobilebilling.dtos;

public class BillDTO {
    private Long subscriberId;
    private int month;
    private int year;

    public BillDTO() {}

    public BillDTO(Long subscriberId, int month, int year) {
        this.subscriberId = subscriberId;
        this.month = month;
        this.year = year;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
