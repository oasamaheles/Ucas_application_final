package com.example.ucasproject.Models;

public class Admin {
    private String adminId;
    private String adminFirstName;
    private String adminAddress;
    private String adminPhoneNumber;
    private String adminImgUrl;

    public Admin() {
    }

    public Admin(String adminFirstName, String adminAddress, String adminPhoneNumber) {
        this.adminFirstName = adminFirstName;
        this.adminAddress = adminAddress;
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminImgUrl() {
        return adminImgUrl;
    }

    public void setAdminImgUrl(String adminImgUrl) {
        this.adminImgUrl = adminImgUrl;
    }
}
