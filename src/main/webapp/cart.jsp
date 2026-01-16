<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.foodapp.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoodFleet | Cart</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cart.css">
</head>

<body>

<jsp:include page="navbar.jsp" />

<section class="cart-page">
  <div class="cart-wrapper">

<%
Map<Integer, CartItem> cart =
    (Map<Integer, CartItem>) session.getAttribute("cart");

double itemTotal = 0;
double gst = 30;
%>

<!-- ================= LEFT : CART ITEMS ================= -->
<div class="cart-left">
  <h1 class="cart-title">Your Cart</h1>

<%
if (cart != null && !cart.isEmpty()) {
  for (CartItem item : cart.values()) {
    double price =
        item.getMenuItem().getPrice() * item.getQuantity();
    itemTotal += price;
%>

  <div class="cart-item">
    <img src="<%=request.getContextPath()%>/<%=item.getMenuItem().getImageUrl()%>" />

    <div class="item-info">
      <h3><%=item.getMenuItem().getItemName()%></h3>
      <p><%=item.getMenuItem().getDescription()%></p>

      <div class="qty-controls">
        <form action="update-cart" method="post">
          <input type="hidden" name="menuId"
                 value="<%=item.getMenuItem().getMenuId()%>">
          <input type="hidden" name="action" value="dec">
          <button type="submit">-</button>
        </form>

        <span><%=item.getQuantity()%></span>

        <form action="update-cart" method="post">
          <input type="hidden" name="menuId"
                 value="<%=item.getMenuItem().getMenuId()%>">
          <input type="hidden" name="action" value="inc">
          <button type="submit">+</button>
        </form>
      </div>
    </div>

    <div class="item-price">₹<%=price%></div>
  </div>

<%
  }
%>

</div>

</aside><!-- ================= RIGHT : BILL SUMMARY ================= -->
<aside class="cart-right">
  <h3>Bill Details</h3>

  <div class="bill-row">
    <span>Item Total</span>
    <span>₹<%=itemTotal%></span>
  </div>

  <div class="bill-row">
    <span>Delivery Fee</span>
    <span class="free">FREE</span>
  </div>

  <div class="bill-row">
    <span>GST & Charges</span>
    <span>₹<%=gst%></span>
  </div>

  <div class="divider"></div>

  <div class="bill-total">
    <span>TO PAY</span>
    <span>₹<%=itemTotal + gst%></pan>
  </div>

  <!-- ✅ GO TO PAYMENT PAGE -->
  <form action="<%=request.getContextPath()%>/checkout" method="get">
  <button type="submit" class="place-order-btn">
    Place Order
  </button>
</form>
</aside>


<%
} else {
%>

<p class="empty">Your cart is empty 🛒</p>

<%
}
%>

  </div>
</section>

</body>
</html>
