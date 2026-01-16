package com.foodapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.model.MenuItem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	

        int restaurantId = Integer.parseInt(req.getParameter("rid"));

        MenuDAO menuDAO = new MenuDAOImpl();
        List<MenuItem> menuList = menuDAO.getMenuByRestaurant(restaurantId);

        System.out.println("RID = " + restaurantId);
        System.out.println("Menu size = " + menuList.size());

        req.setAttribute("menuList", menuList);

        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
    }
}
