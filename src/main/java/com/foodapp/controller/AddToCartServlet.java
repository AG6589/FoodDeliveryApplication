package com.foodapp.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.foodapp.dao.MenuDAO;
import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(req.getParameter("menuId"));

        MenuDAO menuDAO = new MenuDAOImpl();
        MenuItem menuItem = menuDAO.getMenuById(menuId);

        HttpSession session = req.getSession();

        Map<Integer, CartItem> cart =
            (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        if (cart.containsKey(menuId)) {
            CartItem cartItem = cart.get(menuId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cart.put(menuId, new CartItem(menuItem, 1));
        }

        session.setAttribute("cart", cart);
        System.out.println("AddToCart called. menuId = " + menuId);
        System.out.println("Cart size = " + cart.size());



        resp.sendRedirect(req.getHeader("Referer"));
    }
}
