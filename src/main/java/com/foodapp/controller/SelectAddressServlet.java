package com.foodapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.impl.AddressDAOImpl;
import com.foodapp.model.Address;
import com.foodapp.model.User;
import com.foodapp.dao.AddressDAO;

/**
 * Servlet implementation class AddressConfirmServlet
 */
@WebServlet("/select-address")
public class SelectAddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserId();

        AddressDAO dao = new AddressDAOImpl();
        List<Address> addresses = dao.getAddressesByUser(userId);

        req.setAttribute("addresses", addresses);
        req.getRequestDispatcher("select-address.jsp")
           .forward(req, resp);
    }
}
