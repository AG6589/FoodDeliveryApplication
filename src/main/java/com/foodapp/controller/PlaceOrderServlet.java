package com.foodapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.foodapp.model.CartItem;
import com.foodapp.model.User;
import com.foodapp.util.DBUtil;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        // 🔒 SECURITY CHECK
        if (user == null || user.getUserId() <= 0 || cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 💰 CALCULATE TOTAL
        double totalAmount = 0;
        for (CartItem item : cart.values()) {
            totalAmount += item.getMenuItem().getPrice() * item.getQuantity();
        }

        double gst = 30;
        double finalAmount = totalAmount + gst;

        // 💳 PAYMENT METHOD
        String paymentMethod = req.getParameter("payment");
        if (paymentMethod == null || paymentMethod.isBlank()) {
            paymentMethod = "COD";
        }

        Connection con = null;

        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            // ================= INSERT ORDER =================
            String orderSql =
                "INSERT INTO orders " +
                "(UserId, TotalAmount, Gst, FinalAmount, OrderStatus, Payment, CreatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW())";

            PreparedStatement psOrder =
                con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

            psOrder.setInt(1, user.getUserId());
            psOrder.setDouble(2, totalAmount);
            psOrder.setDouble(3, gst);
            psOrder.setDouble(4, finalAmount);
            psOrder.setString(5, "PLACED");
            psOrder.setString(6, paymentMethod);

            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            if (!rs.next()) {
                throw new Exception("Order ID not generated");
            }
            int orderId = rs.getInt(1);

            // ================= INSERT ORDER ITEMS =================
            String itemSql =
                "INSERT INTO order_items (OrderId, MenuId, Quantity, Price) " +
                "VALUES (?, ?, ?, ?)";

            PreparedStatement psItem = con.prepareStatement(itemSql);

            for (CartItem item : cart.values()) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getMenuItem().getMenuId());
                psItem.setInt(3, item.getQuantity());
                psItem.setDouble(4, item.getMenuItem().getPrice());
                psItem.addBatch();
            }

            psItem.executeBatch();
            con.commit(); // ✅ SUCCESS

            // 🧹 CLEAR CART
            session.removeAttribute("cart");

            resp.sendRedirect(
                req.getContextPath() + "/order-success.jsp?orderId=" + orderId
            );
            System.out.println("✅ Order placed successfully. Order ID: " + orderId);


        } catch (Exception e) {
            try {
                if (con != null) con.rollback(); // ❗ VERY IMPORTANT
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/cart.jsp");

        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception ignored) {}
        }
    }
}
