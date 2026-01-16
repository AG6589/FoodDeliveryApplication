<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List,com.foodapp.model.MenuItem" %>



<!DOCTYPE html>
<html lang="en">
<head>
  <title>FoodOrdering| Menu</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<!-- HEADER -->
<section class="menu-header">
  <h1>Menu</h1>
  <p>Pick your favorites — freshly prepared dishes just for you.</p>

  <div class="tabs">
    <button class="active">Recommended</button>
    <button>Veg</button>
    <button>Non-Veg</button>
    <button>Desserts</button>
  </div>
</section>

<!-- GRID -->
<section class="menu-grid">

<%
List<MenuItem> menuList =
    (List<MenuItem>) request.getAttribute("menuList");

if (menuList != null && !menuList.isEmpty()) {
  for (MenuItem item : menuList) {
%>

<article class="menu-card">

  <!-- IMAGE -->
  <div class="menu-img">
    <img
      src="<%=request.getContextPath()%>/<%=item.getImageUrl()%>"
      alt="<%=item.getItemName()%>"
      onerror="this.src='<%=request.getContextPath()%>/images/placeholder.jpg';"
    />

    <span class="badge">Popular</span>
    <span class="price">₹<%=item.getPrice()%></span>
  </div>

  <!-- CONTENT -->
  <div class="menu-content">
    <h3><%=item.getItemName()%></h3>

    <span class="rating">★ 4.5</span>

    <p class="desc"><%=item.getDescription()%></p>

    <div class="meta">
      <span>Food Item</span>
      <span>Ready to serve</span>
    </div>

   <form action="<%=request.getContextPath()%>/add-to-cart" method="post">
    <input type="hidden" name="menuId" value="<%=item.getMenuId()%>">
    <button type="submit" class="btn-add">Add</button>
</form>



</article>

<%
  }
} else {
%>
  <p class="empty">No menu items available for this restaurant.</p>
<% } %>

</section>

</body>
</html>
