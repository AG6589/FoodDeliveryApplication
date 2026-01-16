package com.foodapp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.foodapp.dao.OrderDAO;

@WebServlet("/admin/update-order-status") // ✅ FIXED
public class UpdateOrderStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            String status = req.getParameter("status");

            // ✅ VALID STATUS CHECK
            if (!"PLACED".equals(status)
                    && !"PREPARING".equals(status)
                    && !"DELIVERED".equals(status)) {

                resp.sendRedirect(req.getContextPath() + "/admin/orders?error=invalid");
                return;
            }

            OrderDAO dao = new OrderDAO();
            dao.updateOrderStatus(orderId, status);

            // ✅ BACK TO ADMIN PANEL
            resp.sendRedirect(req.getContextPath() + "/admin/orders?success=true");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/orders?error=true");
        }
    }
}
