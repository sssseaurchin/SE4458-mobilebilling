package com.seaurchin.mobilebilling.entities;

import jakarta.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subscriber subscriber;

    @Column(name = "paid_amount")
    private double paidAmount = 0.0;

    private int month;
    private int year;

    private int totalCallMinutes;
    private int totalInternetMB;

    private double amount;

    private boolean paid;

    public Bill() {
    }

    public Bill(Subscriber subscriber, int month, int year, int totalCallMinutes, int totalInternetMB, double amount, boolean paid) {
        this.subscriber = subscriber;
        this.month = month;
        this.year = year;
        this.totalCallMinutes = totalCallMinutes;
        this.totalInternetMB = totalInternetMB;
        this.amount = amount;
        this.paid = paid;
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
    public double getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }
    public void addPaidAmount(double paidAmount) {
        this.paidAmount += paidAmount;
        if (paidAmount > amount) {
            paidAmount = amount;
        }
    }

}
