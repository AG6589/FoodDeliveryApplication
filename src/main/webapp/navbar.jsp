<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Map,com.foodapp.model.CartItem,com.foodapp.model.User" %>

<%
User user = (User) session.getAttribute("user");

Map<Integer, CartItem> cart =
    (Map<Integer, CartItem>) session.getAttribute("cart");

int cartCount = 0;
if (cart != null) {
    for (CartItem ci : cart.values()) {
        cartCount += ci.getQuantity();
    }
}
%>

<header class="navbar">

  <!-- LOGO -->
  <div class="logo">
    <a href="<%=request.getContextPath()%>/index.jsp"
       style="text-decoration:none;color:inherit;">
      FoodFleet
    </a>
  </div>

  <!-- NAV LINKS -->
  <nav class="nav-links">

    <% if (user == null) { %>
        <!-- NOT LOGGED IN -->
        <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
        <a href="<%=request.getContextPath()%>/register.jsp">Register</a>
    <% } else { %>
        <!-- LOGGED IN -->
    
        <a href="<%=request.getContextPath()%>/cart.jsp" class="cart-link">
          Cart
          <% if (cartCount > 0) { %>
            <span class="cart-badge"><%= cartCount %></span>
          <% } %>
        </a>

        <a href="<%=request.getContextPath()%>/profile.jsp">Profile</a>
        <a href="<%=request.getContextPath()%>/logout">Logout</a>
        <a href="<%=request.getContextPath()%>/orders">Orders</a>
        <a href="<%=request.getContextPath()%>/admin/orders">Admin</a>
        
    <% } %>

  </nav>

  <!-- RIGHT ACTIONS -->
  <div class="nav-right">

    <% if (user != null) { %>
        <input type="text"
               placeholder="Search menu items..."
               class="nav-search">

        <% if (cartCount > 0) { %>
          <a href="<%=request.getContextPath()%>/select-address.jsp"
             class="checkout-btn">
            Checkout
          </a>
        <% } else { %>
          <button class="checkout-btn disabled" disabled>
            Checkout
          </button>
        <% } %>
    <% } %>

  </div>

</header>
