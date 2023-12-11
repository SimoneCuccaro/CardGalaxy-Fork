<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/cards.css">
    <%@include file="../utils/head.jsp"%>
    <title>Shop Now</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome"> Discover Our Products </h1>
        <div class="container">
            <div class="card">
                <div class="imgBx">
                    <img src="${contextPath}/photos/steam.png" alt="steam gift card 20 euro" class="gift-img">
                </div>
                <div class="contentBx">
                    <h2> Steam Card 20€ </h2>
                    <p> Description of the Product</p>
                    <br>
                    <button type="submit"> SHOW REVIEWS </button>
                    <br>
                    <button type="submit"> ADD TO CART </button>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>