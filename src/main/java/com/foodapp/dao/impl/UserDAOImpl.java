package com.foodapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;
import com.foodapp.util.DBUtil;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User loginUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                // ✅ MOST IMPORTANT LINE
                user.setUserId(rs.getInt("UserId"));

                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // optional

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}