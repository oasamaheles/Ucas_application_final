package com.example.ucasproject.Models;

public class User {
    private String userId;
    private String userFirstName;
    private String userAddress;
    private String userSubNumber;
    private String userPhoneNumber;
    private String userImgUrl;

    public User() {
    }

    public User(String userFirstName, String userAddress, String userSubNumber, String userPhoneNumber) {
        this.userFirstName = userFirstName;
        this.userAddress = userAddress;
        this.userSubNumber = userSubNumber;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSubNumber() {
        return userSubNumber;
    }

    public void setUserSubNumber(String userSubNumber) {
        this.userSubNumber = userSubNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }
}
