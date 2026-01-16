package com.foodapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.foodapp.model.OrderDTO;
import com.foodapp.model.User;
import com.foodapp.util.DBUtil;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        List<OrderDTO> orders = new ArrayList<>();

        String sql =
            "SELECT OrderId, FinalAmount, OrderStatus, CreatedAt " +
            "FROM orders WHERE UserId = ? ORDER BY CreatedAt DESC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO o = new OrderDTO();
                o.setOrderId(rs.getInt("OrderId"));
                o.setFinalAmount(rs.getDouble("FinalAmount"));
                o.setStatus(rs.getString("OrderStatus"));
                o.setCreatedAt(rs.getString("CreatedAt"));
                orders.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("order-history.jsp").forward(req, resp);
    }
}
