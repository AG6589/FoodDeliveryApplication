package com.foodapp.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.dao.impl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        RestaurantDAO dao = new RestaurantDAOImpl();
        List<Restaurant> allRestaurants = dao.getAllRestaurants();

        req.setAttribute("allRestaurants", allRestaurants);
        req.getRequestDispatcher("/restaurants.jsp").forward(req, res);
    }
}
