package com.foodapp.dao.impl;

import java.sql.*;
import java.util.*;

import com.foodapp.dao.AddressDAO;
import com.foodapp.model.Address;
import com.foodapp.util.DBUtil;

public class AddressDAOImpl implements AddressDAO {

    // ================= GET ADDRESSES =================
    @Override
    public List<Address> getAddressesByUser(int userId) {

        List<Address> list = new ArrayList<>();
        String sql =
            "SELECT * FROM address WHERE UserID = ? ORDER BY IsDefault DESC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Address a = new Address();

                a.setAddressId(rs.getInt("AddressID"));
                a.setUserId(rs.getInt("UserID"));
                a.setLabel(rs.getString("Label"));
                a.setHouseNo(rs.getString("HouseNo"));
                a.setStreet(rs.getString("Street"));
                a.setArea(rs.getString("Area"));
                a.setCity(rs.getString("City"));
                a.setState(rs.getString("State"));      // ✅ FIXED
                a.setPincode(rs.getString("PinCode"));
                a.setDefault(rs.getBoolean("IsDefault"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= SET DEFAULT ADDRESS =================
    @Override
    public void setDefaultAddress(int userId, int addressId) {

        try (Connection con = DBUtil.getConnection()) {

            // Remove previous default
            PreparedStatement ps1 =
                con.prepareStatement(
                    "UPDATE address SET IsDefault = 0 WHERE UserID = ?");
            ps1.setInt(1, userId);
            ps1.executeUpdate();

            // Set new default
            PreparedStatement ps2 =
                con.prepareStatement(
                    "UPDATE address SET IsDefault = 1 WHERE AddressID = ?");
            ps2.setInt(1, addressId);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ADD ADDRESS =================
    @Override
    public void addAddress(Address address) {

        String sql =
            "INSERT INTO address " +
            "(UserID, Label, HouseNo, Street, Area, City, State, PinCode, IsDefault) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getLabel());
            ps.setString(3, address.getHouseNo());
            ps.setString(4, address.getStreet());
            ps.setString(5, address.getArea());
            ps.setString(6, address.getCity());
            ps.setString(7, address.getState());
            ps.setString(8, address.getPincode());
            ps.setBoolean(9, address.isDefault());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void saveAddress(Address a) {
		// TODO Auto-generated method stub
		
	}
}
