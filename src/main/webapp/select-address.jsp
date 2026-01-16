<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.foodapp.model.Address" %>
<%
if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
  <title>Select Address | FoodOrdering</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/address.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<section class="address-container">

  <div class="address-header">
    <h1>Select delivery address</h1>
    <p>Choose where you want your order delivered</p>
  </div>

  <div class="address-grid">

<%
List<Address> addresses =
    (List<Address>) request.getAttribute("addresses");

if (addresses != null && !addresses.isEmpty()) {
  for (Address a : addresses) {
%>

    <!-- SAVED ADDRESS CARD -->
    <div class="address-card <%= a.isDefault() ? "active" : "" %>">

      <div class="card-top">
        <span class="icon">
          <%= "Work".equalsIgnoreCase(a.getLabel()) ? "🏢" : "🏠" %>
        </span>
        <h3><%= a.getLabel() %></h3>
      </div>

      <p class="address-text">
        <%= a.getHouseNo() %>, <%= a.getStreet() %><br>
        <%= a.getArea() %>, <%= a.getCity() %><br>
        <%= a.getState() %> - <%= a.getPincode() %>
      </p>

      <div class="card-bottom">
        <span class="eta">30–35 mins</span>

        <form action="<%=request.getContextPath()%>/set-default-address" method="post">
          <input type="hidden" name="addressId" value="<%= a.getAddressId() %>">
          <button class="deliver-btn">
            <%= a.isDefault() ? "Delivering Here" : "Deliver Here" %>
          </button>
        </form>
      </div>

    </div>

<%
  }
}
%>

    <!-- ADD NEW ADDRESS CARD -->
    <a href="<%=request.getContextPath()%>/add-address.jsp" class="address-card add-new">
      <div class="add-icon">＋</div>
      <h3>Add New Address</h3>
      <p>Save addresses for faster checkout</p>
      <span class="add-btn">Add New</span>
    </a>

  </div>

</section>

</body>
</html>
