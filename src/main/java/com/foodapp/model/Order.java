package com.foodapp.model;

public class Order {
    private int orderId;
    private int userId;
    private double total;
    private double gst;
    private double finalAmount;
    private String status;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getGst() { return gst; }
    public void setGst(double gst) { this.gst = gst; }

    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
