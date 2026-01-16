package com.foodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderDTO;
import com.foodapp.util.DBUtil;

public class OrderDAO {

    // ================= SAVE ORDER + ITEMS =================
    public int saveOrder(Order order, Map<Integer, CartItem> cart) throws Exception {

        int orderId = 0;
        Connection conn = null;

        String orderSql =
            "INSERT INTO orders (UserId, TotalAmount, Gst, FinalAmount, OrderStatus) " +
            "VALUES (?, ?, ?, ?, ?)";

        String itemSql =
            "INSERT INTO order_items (OrderId, MenuId, Quantity, Price) " +
            "VALUES (?, ?, ?, ?)";

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 🔥 start transaction

            // 1️⃣ Insert Order
            try (PreparedStatement ps =
                    conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, order.getUserId());
                ps.setDouble(2, order.getTotal());
                ps.setDouble(3, order.getGst());
                ps.setDouble(4, order.getFinalAmount());
                ps.setString(5, "PLACED");

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

            // 2️⃣ Insert Order Items
            try (PreparedStatement itemPs = conn.prepareStatement(itemSql)) {
                for (CartItem c : cart.values()) {
                    itemPs.setInt(1, orderId);
                    itemPs.setInt(2, c.getMenuItem().getMenuId());
                    itemPs.setInt(3, c.getQuantity());
                    itemPs.setDouble(4, c.getMenuItem().getPrice());
                    itemPs.addBatch();
                }
                itemPs.executeBatch();
            }

            conn.commit(); // ✅ success
            return orderId;

        } catch (Exception e) {
            if (conn != null) conn.rollback(); // ❗ rollback
            throw e;

        } finally {
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    // ================= UPDATE ORDER STATUS =================
    public void updateOrderStatus(int orderId, String status) throws Exception {

        String sql =
            "UPDATE orders SET OrderStatus = ? WHERE OrderId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    // ================= USER ORDER HISTORY =================
    public List<OrderDTO> getOrdersByUser(int userId) throws Exception {

        List<OrderDTO> list = new ArrayList<>();

        String sql =
            "SELECT OrderId, FinalAmount, OrderStatus, CreatedAt " +
            "FROM orders WHERE UserId = ? ORDER BY CreatedAt DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderId(rs.getInt("OrderId"));
                dto.setFinalAmount(rs.getDouble("FinalAmount"));
                dto.setStatus(rs.getString("OrderStatus"));
                dto.setCreatedAt(
                    rs.getTimestamp("CreatedAt") != null
                        ? rs.getTimestamp("CreatedAt").toString()
                        : "-"
                );
                list.add(dto);
            }
        }
        return list;
    }

    // ================= ADMIN: ALL ORDERS =================
    public List<OrderDTO> getAllOrders() throws Exception {

        List<OrderDTO> list = new ArrayList<>();

        String sql =
            "SELECT OrderId, UserId, FinalAmount, OrderStatus, CreatedAt " +
            "FROM orders ORDER BY CreatedAt DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderId(rs.getInt("OrderId"));
                dto.setUserId(rs.getInt("UserId"));
                dto.setFinalAmount(rs.getDouble("FinalAmount"));
                dto.setStatus(rs.getString("OrderStatus"));
                dto.setCreatedAt(
                    rs.getTimestamp("CreatedAt") != null
                        ? rs.getTimestamp("CreatedAt").toString()
                        : "-"
                );
                list.add(dto);
            }
        }
        return list;
    }
}
