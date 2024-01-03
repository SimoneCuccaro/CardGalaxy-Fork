<%@ page import="java.util.ArrayList" %>
<%@ page import="model.prodotto.GiftCard" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/cards.css">
    <%@include file="../utils/head.jsp" %>
    <title>Shop Now</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp" %>
<%@include file="../partials/sitenavbar.jsp" %>
<main>
    <div class="content">
        <h1 class="welcome"> Discover Our Products </h1>
        <% ArrayList<GiftCard> products = (ArrayList<GiftCard>) request.getAttribute("giftCards");
            for (GiftCard giftCard : products) { %>
        <div class="container">
            <div class="card">
                <div class="imgBx">
                    <img src="${contextPath}/images/<%=giftCard.getFoto()%>" alt="<%=giftCard.getDescrizione()%>"
                         class="gift-img">
                </div>
                <div class="contentBx">
                    <h2><%=giftCard.getNome()%> <%=giftCard.getPrezzo()%>&euro;</h2>
                    <p><%=giftCard.getDescrizione()%></p>
                    <form action="${contextPath}/reviews/show" method="get">
                        <input type="hidden" name="idprodotto" value="<%=giftCard.getId_prodotto()%>">
                        <button type="submit"> SHOW REVIEWS</button>
                    </form>
                    <button type="submit"> ADD TO CART</button>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp" %>
</body>
</html>