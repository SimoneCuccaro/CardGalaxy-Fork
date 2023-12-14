<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Manage Orders</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Manage Orders </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>ID</th>
                <th>FROM</th>
                <th>DATE</th>
                <th>TOTAL</th>
                <th>DETAILS</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ordini}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.id_utente}</td>
                    <td>${order.data_acquisto}</td>
                    <td>${order.prezzo_totale}</td>
                    <form action="${contextPath}/orders/admininfo" method="get">
                        <th><button type="submit" class="button" name="orderid" value="${order.id}">SEE</button> </th>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
