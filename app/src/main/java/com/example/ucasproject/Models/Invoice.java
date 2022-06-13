package com.example.ucasproject.Models;

public class Invoice {
    private String image_user;
    private String numId;
    private String date;
    private String typePayment;
    private String color;

    public Invoice() {
    }

    public Invoice(String image_user, String numId, String date, String typyPayment) {
        this.image_user = image_user;
        this.numId = numId;
        this.date = date;
        this.typePayment = typyPayment;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }


}
