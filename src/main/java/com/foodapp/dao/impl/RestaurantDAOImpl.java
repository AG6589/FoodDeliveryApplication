package com.foodapp.dao.impl;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;
import com.foodapp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

    private Connection connection;

    public RestaurantDAOImpl() {
        connection = DBUtil.getConnection();
    }

    // ADD RESTAURANT
    @Override
    public void addRestaurant(Restaurant restaurant) {

        String sql = "INSERT INTO restaurants " +
                "(Name, cuisine_type, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImageUrl) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisineType());
            stmt.setInt(3, restaurant.getDeliveryTime());
            stmt.setString(4, restaurant.getAddress());
            stmt.setInt(5, restaurant.getAdminUserId());
            stmt.setDouble(6, restaurant.getRating());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setString(8, restaurant.getImageUrl());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET ALL RESTAURANTS
    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurants WHERE IsActive = 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Restaurant r = new Restaurant();

                // ✅ FIXED COLUMN NAME
                r.setRestaurantId(rs.getInt("Restaurants"));
                r.setName(rs.getString("Name"));
                r.setCuisineType(rs.getString("cuisine_type"));
                r.setDeliveryTime(rs.getInt("DeliveryTime"));
                r.setAddress(rs.getString("Address"));
                r.setAdminUserId(rs.getInt("AdminUserID"));
                r.setRating(rs.getDouble("Rating"));
                r.setActive(rs.getBoolean("IsActive"));
                r.setImageUrl(rs.getString("ImageUrl"));

                list.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
