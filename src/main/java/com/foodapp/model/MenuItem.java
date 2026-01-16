package com.foodapp.model;

public class MenuItem {

    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private double price;
    private String imageUrl;

    // getters & setters
    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double d) { this.price = d; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
