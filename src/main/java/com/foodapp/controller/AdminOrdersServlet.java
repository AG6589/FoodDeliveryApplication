package com.foodapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.foodapp.dao.OrderDAO;
import com.foodapp.model.OrderDTO;
import com.foodapp.util.DBUtil;

@WebServlet("/admin/orders")
public class AdminOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection con = DBUtil.getConnection()) {

            OrderDAO dao = new OrderDAO();
            List<OrderDTO> orders = dao.getAllOrders();

            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/admin-orders.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
