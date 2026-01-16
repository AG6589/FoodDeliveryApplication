<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.foodapp.model.User" %>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>FoodOrdering | My Profile</title>

    <!-- NAVBAR CSS -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/navbar.css">

    <!-- PROFILE CSS -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/profile.css">
</head>

<body>

<jsp:include page="navbar.jsp" />

<section class="profile-container">

    <div class="profile-card">

        <div class="profile-header">
            <div class="avatar">
                <span><%= user.getName().substring(0,1).toUpperCase() %></span>
            </div>
            <div>
                <h2><%= user.getName() %></h2>
                <p><%= user.getEmail() %></p>
            </div>
        </div>

        <div class="profile-details">

            <%@ page import="com.foodapp.model.User" %>

<%
User User = (User) session.getAttribute("user");
%>

<div class="profile-card">

    <h2>My Profile</h2>

    <div class="info">
        <label>User ID</label>
        <span><%= user.getUserId() %></span>
    </div>

    <div class="info">
        <label>Name</label>
        <span><%= user.getName() %></span>
    </div>

    <div class="info">
        <label>Email</label>
        <span><%= user.getEmail() %></span>
    </div>

</div>


        </div>

        <div class="profile-actions">
            <a href="<%=request.getContextPath()%>/restaurants" class="btn secondary">
                Back to Restaurants
            </a>

            <a href="<%=request.getContextPath()%>/logout" class="btn danger">
                Logout
            </a>
        </div>

    </div>

</section>

</body>
</html>
