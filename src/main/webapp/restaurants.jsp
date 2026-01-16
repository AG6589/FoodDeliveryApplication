<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FoodOrdering| Restaurants</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/navbar.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/restaurants.css">
</head>

<body>

<jsp:include page="navbar.jsp" />

<!-- HERO -->
<section class="hero">
    <h1>Discover restaurants you’ll love</h1>
    <p>Handpicked options with ratings, ETA, description, and location.</p>
</section>

<!-- GRID -->
<section class="restaurant-grid">

<%
List<Restaurant> allRestaurants =
    (List<Restaurant>) request.getAttribute("allRestaurants");

if (allRestaurants != null) {
    for (Restaurant restaurant : allRestaurants) {
%>

<article class="restaurant-card">
  <a href="<%=request.getContextPath()%>/menu?rid=<%=restaurant.getRestaurantId()%>"
     class="card-link">

    <div class="img-wrap">
      <img src="<%=request.getContextPath()%>/<%=restaurant.getImageUrl()%>"
           alt="<%=restaurant.getName()%>">

      <span class="eta">
        ETA <%=restaurant.getDeliveryTime()%> min
      </span>
    </div>

    <div class="card-body">
      <h3><%=restaurant.getName()%></h3>

      <div class="rating-row">
        <span class="rating">★ <%=restaurant.getRating()%></span>
        <span class="area"><%=restaurant.getAddress()%></span>
      </div>

      <p class="desc"><%=restaurant.getCuisineType()%> food</p>

      <div class="meta">
        <span><%=restaurant.getCuisineType()%></span>
        <span>Free delivery</span>
      </div>
    </div>

  </a>
</article>

<%
    }
}
%>

</section>
