<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <%@include file="../utils/head.jsp" %>
    <title>Shopping Cart</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp" %>
<%@include file="../partials/sitenavbar.jsp" %>
<main>
    <div class="content">
        <c:if test="${sessionScope.cartString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Product removed successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("cartString"); %>
        </c:if>
        <h1 class="welcome">Shopping Cart</h1>
        <div class="grid-y align-center">
            <table class="table purple ut">
                <c:choose>
                    <c:when test="${cart.quantita()==0}">
                        <caption class="visible add-margin-bottom">Cart Is Empty!</caption>
                    </c:when>
                    <c:otherwise>
                        <thead>
                        <tr>
                            <th>PRODUCT</th>
                            <th>PRICE</th>
                            <th>QUANTITY</th>
                            <th>REMOVE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cart.items}" var="cartItems">
                            <tr>
                                <td>${cartItems.giftCard.nome}</td>
                                <td>${cartItems.giftCard.prezzo}&euro;</td>
                                <td>x${cartItems.quantita}</td>
                                <form action="${contextPath}/cart/remove" method="post" onsubmit="return confirm('Are you sure?');">
                                    <input type="hidden" value="${cartItems.giftCard.id_prodotto}" name="idprodotto">
                                    <td>
                                        <button class="button" type="submit">X</button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <div>
                            <form action="${contextPath}/orders/add" method="post" onsubmit="return confirm('Are you sure?');">
                            <button type="submit" class="glowing-btn"> BUY NOW</button>
                            </form>
                        </div>
                        <caption class="visible add-margin-bottom">The current amount is: ${cart.totale()}&euro; for a total of: ${cart.quantita()} cards</caption>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp" %>
</body>
</html>