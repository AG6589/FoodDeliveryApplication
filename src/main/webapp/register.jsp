<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | FoodOrdering</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css">
</head>
<body>

<div class="register-wrapper">

  <div class="register-card">

    <h1 class="logo">FoodFleet</h1>
    <p class="subtitle">Create your account</p>

    <form action="<%=request.getContextPath()%>/register" method="post">

      <div class="field">
        <label>Name</label>
        <input type="text" name="name" placeholder="Your full name" required>
      </div>

      <div class="field">
        <label>Email</label>
        <input type="email" name="email" placeholder="Email address" required>
      </div>

      <div class="field">
        <label>Password</label>
        <input type="password" name="password" placeholder="Create password" required>
      </div>

      <button type="submit" class="register-btn">Create Account</button>

      <p class="login-link">
        Already have an account?
        <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
      </p>

    </form>

  </div>

</div>

</body>
</html>
