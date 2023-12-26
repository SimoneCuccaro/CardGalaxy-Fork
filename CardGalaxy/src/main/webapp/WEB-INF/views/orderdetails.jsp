<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <%@include file="../utils/head.jsp" %>
    <title>Order Details</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp" %>
<%@include file="../partials/sitenavbar.jsp" %>
<main>
    <div class="content">
        <h1 class="welcome">Order Details</h1>
        <div class="grid-y align-center">
            <table class="table purple ut">
                <thead>
                <tr>
                    <th>ID ORDER</th>
                    <th>PRODUCT</th>
                    <th>UNITS</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.prodottoList}" var="product">
                    <tr>
                        <td>${order.id}</td>
                        <td>${product.key.nome} ${product.key.prezzo}&euro;</td>
                        <td>${product.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <form action="${contextPath}/orders/remove" method="post" onsubmit="return confirm('Are you sure?');">
                <button type="submit" class="glowing-btn" value="${order.id}" name="orderid"> DELETE ORDER</button>
                </form>
                <form action="${contextPath}/orders/modify" method="get">
                    <input type="hidden" value="${order.id}" name="orderid">
                    <button type="submit" class="glowing-btn"> EDIT ORDER</button>
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp" %>
</body>
</html>