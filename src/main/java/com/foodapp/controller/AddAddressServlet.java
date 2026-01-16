package com.foodapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.foodapp.dao.AddressDAO;
import com.foodapp.dao.impl.AddressDAOImpl;
import com.foodapp.model.Address;
import com.foodapp.model.User;

/**
 * Servlet implementation class AddAddressServlet
 */
@WebServlet("/add-address")
public class AddAddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Address a = new Address();
        a.setUserId(user.getUserId());
        a.setLabel(req.getParameter("label"));
        a.setHouseNo(req.getParameter("houseNo"));
        a.setStreet(req.getParameter("street"));
        a.setArea(req.getParameter("area"));
        a.setCity(req.getParameter("city"));
        a.setState(req.getParameter("state"));
        a.setPincode(req.getParameter("pincode"));
        a.setDefault(req.getParameter("isDefault") != null);

        AddressDAO dao = new AddressDAOImpl();
        dao.addAddress(a);

        // redirect back to address selection page
        resp.sendRedirect(req.getContextPath() + "/select-address");
    }
}