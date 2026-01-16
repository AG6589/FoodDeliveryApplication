<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.foodapp.model.CartItem"%>
<%@ page import="com.foodapp.model.User"%>

<%
/* ================= LOGIN CHECK ================= */
User user = (User) session.getAttribute("user");

if (user == null) {
    session.setAttribute("redirectAfterLogin", "payment.jsp");
    response.sendRedirect("login.jsp");
    return;
}

/* ================= CART CHECK ================= */
Map<Integer, CartItem> cart =
        (Map<Integer, CartItem>) session.getAttribute("cart");

if (cart == null || cart.isEmpty()) {
    response.sendRedirect("cart.jsp");
    return;
}

/* ================= BILL CALCULATION ================= */
double total = 0;
for (CartItem c : cart.values()) {
    total += c.getMenuItem().getPrice() * c.getQuantity();
}

double gst = 30;
double finalAmount = total + gst;

// 🔐 YOUR UPI ID
String upiId = "foodfleet@upi";
%>

<!DOCTYPE html>
<html>
<head>
<title>Payment | FoodOrdering</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/payment.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="payment-container">
  <div class="payment-card">

    <h2>Payment</h2>
    <p class="subtitle">Choose payment method</p>

    <!-- ================= BILL ================= -->
    <div class="bill">
      <div><span>Item Total</span><span>₹<%=total%></span></div>
      <div><span>GST & Charges</span><span>₹30</span></div>
      <div class="final">
        <span>To Pay</span>
        <span>₹<%=finalAmount%></span>
      </div>
    </div>

    <!-- ================= PAYMENT FORM ================= -->
    <form action="<%=request.getContextPath()%>/place-order" method="post">

      <!-- COD -->
      <label class="payment-option">
        <input type="radio" name="payment" value="COD" checked
               onclick="showPayment('cod')">
        <span>Cash on Delivery</span>
      </label>

      <!-- UPI -->
      <label class="payment-option">
        <input type="radio" name="payment" value="UPI"
               onclick="showPayment('upi')">
        <span>UPI</span>
      </label>

      <!-- CARD -->
      <label class="payment-option">
        <input type="radio" name="payment" value="CARD"
               onclick="showPayment('card')">
        <span>Debit / Credit Card</span>
      </label>

      <!-- ================= DYNAMIC AREA ================= -->
      <div class="payment-dynamic">

        <!-- COD BOX -->
        <div id="codBox" class="pay-box">
          <p class="cod-text">
            Pay with cash when your order is delivered 🚚
          </p>
        </div>

        <!-- UPI BOX -->
        <div id="upiBox" class="pay-box hidden">
          <p class="upi-title">Scan & Pay</p>

          <img class="qr-img"
               src="https://api.qrserver.com/v1/create-qr-code/?size=260x260&data=upi://pay?pa=<%=upiId%>&pn=FoodFleet&am=<%=finalAmount%>&cu=INR&tn=FoodFleetOrder"
               alt="UPI QR">

          <p class="upi-note">
            Use Google Pay / PhonePe / Paytm
          </p>
        </div>

        <!-- CARD BOX -->
        <div id="cardBox" class="pay-box hidden">
          <input type="text" name="cardNumber"
                 placeholder="Card Number" maxlength="16">

          <div class="card-row">
            <input type="text" name="expiry"
                   placeholder="MM/YY" maxlength="5">
            <input type="password" name="cvv"
                   placeholder="CVV" maxlength="3">
          </div>

          <input type="text" name="cardName"
                 placeholder="Card Holder Name">
        </div>

      </div>

      <!-- SUBMIT -->
      <button type="submit" class="place-order-btn">
        Pay ₹<%=finalAmount%>
      </button>

    </form>

  </div>
</div>

<script>
function showPayment(type) {
  const cod  = document.getElementById("codBox");
  const upi  = document.getElementById("upiBox");
  const card = document.getElementById("cardBox");

  cod.classList.add("hidden");
  upi.classList.add("hidden");
  card.classList.add("hidden");

  if (type === "cod") cod.classList.remove("hidden");
  if (type === "upi") upi.classList.remove("hidden");
  if (type === "card") card.classList.remove("hidden");

  // Card fields required only when card selected
  document.querySelectorAll("#cardBox input").forEach(input => {
    input.required = (type === "card");
  });
}
</script>

</body>
</html>
