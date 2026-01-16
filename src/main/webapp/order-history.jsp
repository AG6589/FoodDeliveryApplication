<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.OrderDTO" %>

<!DOCTYPE html>
<html>
<head>
<title>FoodOrdering | Order History</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">

<style>
/* PAGE */
.order-page {
  padding: 70px 8%;
}

/* TITLE */
.order-page h1 {
  font-size: 32px;
  color: #f1f5f9;
  margin-bottom: 30px;
}

/* EMPTY STATE */
.empty {
  color: #94a3b8;
  font-size: 16px;
}

/* CARD */
.order-card {
  background: linear-gradient(135deg, #020617, #030712);
  border: 1px solid #1e293b;
  border-radius: 18px;
  padding: 22px 26px;
  margin-bottom: 22px;
}

/* HEADER */
.order-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

/* LEFT INFO */
.order-info p {
  margin-bottom: 6px;
  font-size: 15px;
  color: #e5e7eb;
}

.order-info strong {
  color: #94a3b8;
  font-weight: 500;
}

/* STATUS BADGE */
.status {
  padding: 8px 18px;
  border-radius: 999px;
  font-weight: 700;
  font-size: 13px;
  text-transform: uppercase;
}

.status.PLACED {
  background: #16a34a;
  color: #022c22;
}

.status.PREPARING {
  background: #facc15;
  color: #422006;
}

.status.DELIVERED {
  background: #22c55e;
  color: #022c22;
}

/* PROGRESS BAR */
.progress-wrapper {
  display: flex;
  align-items: center;
  margin-top: 18px;
}

.progress-step {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 6px;
  min-width: 90px;
}

.progress-step span {
  width: 34px;
  height: 34px;
  border-radius: 50%;
  background: #020617;
  border: 2px solid #334155;
  color: #64748b;
  font-weight: 700;
  display: flex;
  align-items: center;
  justify-content: center;
}

.progress-step p {
  font-size: 12px;
  color: #64748b;
}

/* LINE */
.progress-line {
  flex: 1;
  height: 3px;
  background: #334155;
  margin: 0 8px;
}

/* ACTIVE */
.progress-step.active span {
  background: #22c55e;
  border-color: #22c55e;
  color: #022c22;
}

.progress-step.active p {
  color: #22c55e;
}

.progress-line.active {
  background: linear-gradient(90deg, #22c55e, #16a34a);
}
</style>
</head>

<body>

<jsp:include page="navbar.jsp"/>

<section class="order-page">
  <h1>Your Orders</h1>

<%
List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");

if (orders == null || orders.isEmpty()) {
%>
  <p class="empty">You have no orders yet 🛒</p>
<%
} else {
  for (OrderDTO o : orders) {

    int step = 1;
    if ("PREPARING".equals(o.getStatus())) step = 2;
    else if ("DELIVERED".equals(o.getStatus())) step = 3;
%>

  <div class="order-card">

    <div class="order-header">
      <div class="order-info">
        <p><strong>Order ID:</strong> #<%= o.getOrderId() %></p>
        <p><strong>Date:</strong> <%= o.getCreatedAt() %></p>
        <p><strong>Total:</strong> ₹<%= o.getFinalAmount() %></p>
      </div>

      <span class="status <%= o.getStatus() %>"><%= o.getStatus() %></span>
    </div>

    <!-- PROGRESS BAR -->
    <div class="progress-wrapper">
      <div class="progress-step <%= step >= 1 ? "active" : "" %>">
        <span>1</span>
        <p>Placed</p>
      </div>

      <div class="progress-line <%= step >= 2 ? "active" : "" %>"></div>

      <div class="progress-step <%= step >= 2 ? "active" : "" %>">
        <span>2</span>
        <p>Preparing</p>
      </div>

      <div class="progress-line <%= step >= 3 ? "active" : "" %>"></div>

      <div class="progress-step <%= step >= 3 ? "active" : "" %>">
        <span>3</span>
        <p>Delivered</p>
      </div>
    </div>

  </div>

<%
  }
}
%>

</section>
</body>
</html>
