package com.seaurchin.mobilebilling.dtos;

public class UsageLogDTO {

    private Long subscriberId;
    private String type;
    private int month;
    private int year;

    public UsageLogDTO() {}

    public UsageLogDTO(Long subscriberId, String type) {
        this.subscriberId = subscriberId;
        this.type = type;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
