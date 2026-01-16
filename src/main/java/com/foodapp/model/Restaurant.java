package com.foodapp.model;

public class Restaurant {

    private int restaurantId;      // PK
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private int adminUserId;
    private double rating;
    private boolean isActive;
    private String imageUrl;

    // ✅ No-arg constructor (MANDATORY for JSP/JDBC)
    public Restaurant() {
    }

    // ✅ Parameterized constructor
    public Restaurant(int restaurantId, String name, String cuisineType,
                      int deliveryTime, String address, int adminUserId,
                      double rating, boolean isActive, String imageUrl) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    // ✅ Getters & Setters

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // ✅ toString() — VERY IMPORTANT FOR DEBUGGING
    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisineType + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", deliveryTime=" + deliveryTime +
                ", isActive=" + isActive +
                ", adminUserId=" + adminUserId +
                '}';
    }


}
