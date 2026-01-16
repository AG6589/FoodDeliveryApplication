package com.foodapp.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.MenuItem;
import com.foodapp.util.DBUtil;

public class MenuDAOImpl implements MenuDAO {

    // ✅ FETCH MENU BY RESTAURANT
    @Override
    public List<MenuItem> getMenuByRestaurant(int restaurantId) {

        List<MenuItem> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE RestaurantID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setMenuId(rs.getInt("MenuID"));
                item.setRestaurantId(rs.getInt("RestaurantID"));
                item.setItemName(rs.getString("ItemName"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setImageUrl(rs.getString("ImageUrl"));
                menuList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }

    // ✅ FETCH SINGLE MENU ITEM (FOR CART)
    @Override
    public MenuItem getMenuById(int menuId) {

        MenuItem item = null;
        String sql = "SELECT * FROM menu WHERE MenuID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new MenuItem();
                item.setMenuId(rs.getInt("MenuID"));
                item.setRestaurantId(rs.getInt("RestaurantID"));
                item.setItemName(rs.getString("ItemName"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setImageUrl(rs.getString("ImageUrl"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }
}
