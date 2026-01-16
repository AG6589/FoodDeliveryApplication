<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>FoodOrdering | Home</title>
  <link rel="stylesheet" href="css/navbar.css">
  <link rel="stylesheet" href="css/index.css">
</head>

<body>

<jsp:include page="navbar.jsp" />

<!-- HERO -->
<section class="hero">
  <h1>Taste the Extraordinary</h1>
</section>

<!-- SHOWCASE -->
<section class="showcase">
  <h2>Our Signature Dishes</h2>

  <div class="cards">
    <div class="card">
      <img src="images/pizza.jpg">
      <h3>Margherita Pizza</h3>
      <p>$14.50</p>
    </div>

    <div class="card">
      <img src="images/burger.jpg">
      <h3>Gourmet Cheeseburger</h3>
      <p>$16.00</p>
    </div>

    <div class="card">
      <img src="images/pasta.jpg">
      <h3>Spaghetti Carbonara</h3>
      <p>$18.50</p>
    </div>
  </div>
</section>

</body>
</html>
