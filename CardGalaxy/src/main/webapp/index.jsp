<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" href="${contextPath}/CSS/support-button.css">
    <title>Homepage</title>
    <%@include file="./WEB-INF/utils/head.jsp"%>
</head>
<body>
<%@include file="./WEB-INF/partials/siteheader.jsp"%>
<%@include file="./WEB-INF/partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Welcome to the Galaxy of Gift Cards</h1>
        <img class="welcome-animation" src="${contextPath}/photos/card_anim_welcome.gif" alt="card animation">
        <p class="para_index">
            All the Gift Cards that you need in ONE PLACE.
        </p>
        <img class="promo-cards" src="${contextPath}/photos/cards_promo.png" alt="promo cards">
        <p class="para_index">
             What are you waiting for? <a class="index-link" href="${contextPath}/products/shopnow">SHOP NOW</a>
        </p>
    </div>
</main>
<%@include file="./WEB-INF/partials/sitefooter.jsp"%>
</body>
</html>
