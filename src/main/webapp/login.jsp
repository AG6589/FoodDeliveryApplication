<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | FoodFleet</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>

<div class="login-wrapper">

  <div class="login-card">

    <h1 class="logo">FoodOrdering</h1>
    <p class="subtitle">Login to continue ordering</p>

    <form action="<%=request.getContextPath()%>/login" method="post">

      <div class="field">
        <label>Email</label>
        <input type="email" name="email" placeholder="Enter your email" required>
      </div>

      <div class="field">
        <label>Password</label>
        <input type="password" name="password" placeholder="Enter your password" required>
      </div>

      <button type="submit" class="login-btn">Login</button>

    </form>

  </div>

</div>

</body>
</html>
