<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <%@include file="../utils/head.jsp"%>
    <title>Order History</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <c:if test="${sessionScope.removeOrder==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Order removed successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("removeOrder"); %>
        </c:if>
        <h1 class="welcome">All Your Orders </h1>
        <div class="grid-y align-center">
        <table class="table purple ut">
            <thead>
            <tr>
                <th>ID</th>
                <th>DATA</th>
                <th>TOTAL</th>
                <th>DETAILS</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.data_acquisto}</td>
                    <td>${order.prezzo_totale}&euro;</td>
                    <form action="${contextPath}/orders/info" method="get">
                        <input type="hidden" value="${order.id}" name="orderid">
                        <td><button type="submit" class="button">VIEW</button> </td>
                    </form>
                 </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>