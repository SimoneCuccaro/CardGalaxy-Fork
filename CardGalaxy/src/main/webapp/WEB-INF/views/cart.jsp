<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
  <%@include file="../utils/head.jsp"%>
  <title>Shopping Cart</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
  <div class="content">
    <h1 class="welcome">Shopping Cart</h1>
    <div class="grid-y align-center">
      <table class="table purple ut">
        <thead>
        <tr>
          <th>PRODUCT</th>
          <th>UNITS</th>
          <th>TOTAL PRICE</th>
          <th>REMOVE</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>PlayStation Card 20&euro;</td>
          <td>4</td>
          <td>80&euro;</td>
          <td><button class=button type="submit">CONFIRM</button> </td>
        </tr>
        </tbody>
      </table>
      <div>
        <button type="submit" class="glowing-btn"> BUY NOW </button>
      </div>
    </div>
  </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>