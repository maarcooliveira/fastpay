package com.mastercard.mymerchant.model.fastpay;

/**
 * SmartPay project
 * Created by marco on 12/06/2016.
 */

public class Transaction {
    private String date;
    private String hour;
    private Double value;

    public Transaction(String date, String hour, Double value) {
        this.date = date;
        this.hour = hour;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
