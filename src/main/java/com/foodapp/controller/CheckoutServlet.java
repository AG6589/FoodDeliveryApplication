package com.foodapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            // ❌ Not logged in → save redirect & go login
            session.setAttribute("redirectAfterLogin", "payment");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // ✅ Logged in → go payment
            resp.sendRedirect(req.getContextPath() + "/payment.jsp");
        }
    }
}