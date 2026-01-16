package com.foodapp.model;

public class OrderDTO {

    private int orderId;
    private int userId;
    private double finalAmount;
    private String status;
    private String createdAt;

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
