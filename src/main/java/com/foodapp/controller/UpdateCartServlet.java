package com.foodapp.controller;

import java.io.IOException;
import java.util.Map;

import com.foodapp.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(req.getParameter("menuId"));
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        Map<Integer, CartItem> cart =
            (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(menuId)) {

            CartItem cartItem = cart.get(menuId);

            if ("inc".equals(action)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }

            if ("dec".equals(action)) {
                int qty = cartItem.getQuantity() - 1;

                if (qty <= 0) {
                    cart.remove(menuId);
                } else {
                    cartItem.setQuantity(qty);
                }
            }
        }

        session.setAttribute("cart", cart);

        // 🔁 stay on cart page
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
