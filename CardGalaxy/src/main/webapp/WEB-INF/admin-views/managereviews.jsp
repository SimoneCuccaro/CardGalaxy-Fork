<%@ page import="java.util.ArrayList" %>
<%@ page import="model.prodotto.GiftCard" %>
<%@ page import="model.recensione.Recensione" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Manage Reviews</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Manage Reviews</h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>FROM</th>
                <th>PRODUCT</th>
                <th>TEXT</th>
                <th>DATE</th>
                <th>REMOVE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <td>${review.id_utente}</td>
                    <td>${review.id_prodotto}</td>
                    <td>${review.testo}</td>
                    <td>${review.datarecensione}</td>
                    <form action="${contextPath}/reviews/remove" method="post" onsubmit="return confirm('Are you sure?');">
                        <td><button type="submit" class="button" name="giftid" value="${review.id_prodotto}"> X </button></td>
                        <input type="hidden" name="userid" value="${review.id_utente}">
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>