package com.example.ucasproject.Models;

import java.io.Serializable;

public class Issuing implements Serializable {
    private String name;
    private String numId;
    private String currentReading;
    private String bill;

    public Issuing() {
    }

    public Issuing(String name, String numId, String currentReading, String bill) {
        this.name = name;
        this.numId = numId;
        this.currentReading = currentReading;
        this.bill = bill;
    }

    public String getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(String currentReading) {
        this.currentReading = currentReading;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
