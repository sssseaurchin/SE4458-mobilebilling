package com.seaurchin.mobilebilling.dtos;

public class BillDetailsDTO {
    private int month;
    private int year;
    private int totalCallMinutes;
    private int totalInternetMB;
    private double amount;
    private boolean paid;

    public BillDetailsDTO() {}

    public BillDetailsDTO(int month, int year, int totalCallMinutes, int totalInternetMB, double amount, boolean paid) {
        this.month = month;
        this.year = year;
        this.totalCallMinutes = totalCallMinutes;
        this.totalInternetMB = totalInternetMB;
        this.amount = amount;
        this.paid = paid;
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

    public int getTotalCallMinutes() {
        return totalCallMinutes;
    }

    public void setTotalCallMinutes(int totalCallMinutes) {
        this.totalCallMinutes = totalCallMinutes;
    }

    public int getTotalInternetMB() {
        return totalInternetMB;
    }

    public void setTotalInternetMB(int totalInternetMB) {
        this.totalInternetMB = totalInternetMB;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
