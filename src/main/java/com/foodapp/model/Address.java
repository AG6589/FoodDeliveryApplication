package com.foodapp.model;

public class Address {

    private int addressId;
    private int userId;
    private String label;
    private String houseNo;
    private String street;
    private String area;
    private String city;
    private String state;
    private String pincode;
    private boolean isDefault;

    // ===== GETTERS =====

    public int getAddressId() {
        return addressId;
    }

    public int getUserId() {
        return userId;
    }

    public String getLabel() {
        return label;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getStreet() {
        return street;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public boolean isDefault() {
        return isDefault;
    }

    // ===== SETTERS =====

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
