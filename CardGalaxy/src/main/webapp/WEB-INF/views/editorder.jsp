<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Edit Order</title>
    <%@include file="../utils/head.jsp" %>
</head>
<body>
<%@include file="../partials/siteheader.jsp" %>
<%@include file="../partials/sitenavbar.jsp" %>
<main>
    <div class="content">
        <c:if test="${sessionScope.updateDone==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Product update successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("updateDone"); %>
        </c:if>
        <h1 class="welcome">Edit Your Order</h1>
        <c:forEach items="${sessionScope.order.prodottoList}" var="product">
            <form class="login grid-y" action="${contextPath}/orders/update" method="post">
                <span>${product.key.nome} ${product.key.prezzo}&euro;</span>
                <label for="quantityid">
                    <input class="larger" type="number" min="1" max="10" name="quantity" id="quantityid"
                           value="${product.value}"
                           required>
                    <input type="hidden" value="${sessionScope.order.id}" name="orderid">
                    <input type="hidden" value="${product.key.id_prodotto}" name="productid">
                </label>
                <button class="button" type="submit"> EDIT PRODUCT</button>
                <br>
            </form>
        </c:forEach>
        <form action="${contextPath}/orders/info" method="get">
            <input type="hidden" name="orderid" value="${sessionScope.order.id}">
            <button class="button" type="submit"> BACK</button>

        </form>
        <br>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp" %>
</body>
</html>