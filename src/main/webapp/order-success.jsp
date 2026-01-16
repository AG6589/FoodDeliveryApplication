<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Order Successful | FoodOrdering</title>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: system-ui, -apple-system, Segoe UI, sans-serif;
  }

  body {
    background: radial-gradient(circle at top, #111827, #020617);
    color: #e5e7eb;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .success-card {
    background: #020617;
    border: 1px solid #1e293b;
    border-radius: 18px;
    padding: 40px 36px;
    width: 100%;
    max-width: 420px;
    text-align: center;
    box-shadow: 0 20px 50px rgba(0,0,0,0.6);
  }

  .icon {
    font-size: 52px;
    margin-bottom: 14px;
  }

  h2 {
    font-size: 26px;
    margin-bottom: 10px;
    color: #f1f5f9;
  }

  .order-id {
    font-size: 15px;
    color: #94a3b8;
    margin-bottom: 22px;
  }

  .order-id span {
    color: #38bdf8;
    font-weight: 700;
  }

  .message {
    font-size: 15px;
    color: #cbd5f5;
    line-height: 1.5;
    margin-bottom: 28px;
  }

  .actions {
    display: flex;
    gap: 12px;
    justify-content: center;
  }

  .btn {
    padding: 12px 22px;
    border-radius: 999px;
    font-weight: 700;
    text-decoration: none;
    font-size: 14px;
  }

  .btn.primary {
    background: linear-gradient(135deg, #22d3ee, #3b82f6);
    color: #020617;
  }

  .btn.secondary {
    background: rgba(255,255,255,0.08);
    color: #e5e7eb;
  }

  .btn:hover {
    opacity: 0.9;
  }
</style>
</head>

<body>

<div class="success-card">

  <div class="icon">🎉</div>

  <h2>Order Placed Successfully</h2>

  <p class="order-id">
    Order ID :
    <span>
      <%= request.getParameter("orderId") != null
            ? request.getParameter("orderId")
            : "Generated" %>
    </span>
  </p>

  <p class="message">
    Thank you for ordering with <strong>FoodFleet</strong>.<br>
    Your delicious food is being prepared and will reach you soon 🍽️
  </p>

  <div class="actions">
    <a href="<%=request.getContextPath()%>/restaurants" class="btn primary">
      Browse Restaurants
    </a>
    <a href="<%=request.getContextPath()%>/profile.jsp" class="btn secondary">
      View Profile
    </a>
  </div>

</div>



</body>
</html>
