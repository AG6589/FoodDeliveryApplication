package com.foodapp.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.foodapp.dao.UserDAO;
import com.foodapp.dao.impl.UserDAOImpl;
import com.foodapp.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAOImpl();
        User user1 = dao.loginUser(email, password);

        if (user1 != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user1);

            String redirect = (String) session.getAttribute("redirectAfterLogin");
            if (redirect != null) {
                session.removeAttribute("redirectAfterLogin");
                res.sendRedirect(req.getContextPath() + "/" + redirect);
            } else {
                res.sendRedirect(req.getContextPath() + "/restaurants");
            }
        }
    }
}


