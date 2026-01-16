<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.OrderDTO" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin | Order Management</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
<style>
body {
  background: #020617;
  color: #e5e7eb;
  font-family: system-ui;
  padding: 40px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 14px;
  border-bottom: 1px solid #1e293b;
}

select {
  padding: 6px 10px;
  border-radius: 8px;
}

button {
  padding: 6px 14px;
  border-radius: 8px;
  background: #22c55e;
  border: none;
  font-weight: 700;
  cursor: pointer;
}
status-badge {
  padding: 6px 14px;
  border-radius: 999px;
  font-weight: 700;
  font-size: 13px;
}

status-PLACED {
  background: #16a34a;
  color: #022c22;
}

status-PREPARING {
  background: #facc15;
  color: #422006;
}

status-DELIVERED {
  background: #2563eb;
  color: #e0f2fe;
}

</style>
</head>

<body>

<h1>Admin – Manage Orders</h1>

<table>
<tr>
  <th>Order ID</th>
  <th>User ID</th>
  <th>Total</th>
  <th>Status</th>
  <th>Action</th>
</tr>

<%
List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
for (OrderDTO o : orders) {
%>

<tr>
<td>#<%= o.getOrderId() %></td>
<td><%= o.getUserId() %></td>
<td>₹<%= o.getFinalAmount() %></td>
<td><%= o.getStatus() %></td>

<td>
<form action="<%=request.getContextPath()%>/admin/update-order-status" method="post">
  <input type="hidden" name="orderId" value="<%= o.getOrderId() %>">

  <select name="status" class="status-select">
    <option value="PLACED"
      <%= o.getStatus().equals("PLACED") ? "selected" : "" %>>
      PLACED
    </option>

    <option value="PREPARING"
      <%= o.getStatus().equals("PREPARING") ? "selected" : "" %>
      <%= o.getStatus().equals("DELIVERED") ? "disabled" : "" %>>
      PREPARING
    </option>

    <option value="DELIVERED"
      <%= o.getStatus().equals("DELIVERED") ? "selected" : "" %>>
      DELIVERED
    </option>
  </select>

  <button type="submit"
    <%= o.getStatus().equals("DELIVERED") ? "disabled" : "" %>>
    Update
  </button>
</form>
</td>

</tr>

<% } %>

</table>

</body>
</html>
