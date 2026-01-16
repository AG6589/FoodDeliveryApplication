package com.foodapp.controller;

import java.io.IOException;

import com.foodapp.dao.impl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect(req.getContextPath() + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDAOImpl dao = new UserDAOImpl();
        boolean success = dao.registerUser(user);

        if (success) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            res.sendRedirect(req.getContextPath() + "/register.jsp?error=true");
        }
    }
}
