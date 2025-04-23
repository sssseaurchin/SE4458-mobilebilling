package com.seaurchin.mobilebilling.dtos;

public class BillSummaryDTO {
    private double amount;
    private boolean paid;

    public BillSummaryDTO() {}

    public BillSummaryDTO(double amount, boolean paid) {
        this.amount = amount;
        this.paid = paid;
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
