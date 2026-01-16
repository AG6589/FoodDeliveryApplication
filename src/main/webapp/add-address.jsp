<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Address | FoodOrdering</title>

  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/address.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<section class="add-address-page">

  <div class="form-card">

    <h1>Add New Address</h1>
    <p class="subtitle">Where should we deliver your food?</p>

    <form action="<%=request.getContextPath()%>/add-address" method="post">

      <!-- LABEL -->
      <div class="form-group">
        <label>Address Label</label>
        <select name="label">
          <option>Home</option>
          <option>Work</option>
          <option>Other</option>
        </select>
      </div>

      <!-- HOUSE -->
      <div class="form-group">
        <label>House / Flat No</label>
        <input type="text" name="houseNo" placeholder="Flat / House number" required>
      </div>

      <!-- STREET -->
      <div class="form-group">
        <label>Street</label>
        <input type="text" name="street" placeholder="Street name" required>
      </div>

      <!-- AREA -->
      <div class="form-group">
        <label>Area</label>
        <input type="text" name="area" placeholder="Area / Locality" required>
      </div>

      <!-- CITY & STATE -->
      <div class="form-row">
        <div class="form-group">
          <label>City</label>
          <input type="text" name="city" placeholder="City" required>
        </div>

        <div class="form-group">
          <label>State</label>
          <input type="text" name="state" placeholder="State" required>
        </div>
      </div>

      <!-- PIN -->
      <div class="form-group">
        <label>Pincode</label>
        <input type="text" name="pincode" placeholder="6-digit pincode" required>
      </div>

      <!-- DEFAULT -->
      <div class="checkbox-row">
        <input type="checkbox" name="isDefault" value="true">
        <span>Make this my default address</span>
      </div>

      <button class="save-btn">Save Address</button>

    </form>

  </div>

</section>

</body>
</html>
