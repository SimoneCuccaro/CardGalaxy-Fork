<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Manage Products</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Manage Products</h1>
        <form action="${contextPath}/products/add" method="get">
        <button type="submit" class="button add-margin-bottom">ADD A PRODUCT</button>
        </form>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>PLATFORM</th>
                <th>DESCRIPTION</th>
                <th>PRICE</th>
                <th>DELETE</th>
                <th>EDIT</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${giftCards}" var="giftCard">
                <tr>
                    <td>${giftCard.id_prodotto}</td>
                    <td>${giftCard.nome}</td>
                    <td>${giftCard.piattaforma}</td>
                    <td>${giftCard.descrizione}</td>
                    <td>${giftCard.prezzo}</td>
                    <form action="${contextPath}/products/remove" method="post" onsubmit="return confirm('Are you sure?');">
                        <td><button type="submit" class="button" name="giftid" value="${giftCard.id_prodotto}"> X </button></td>
                    </form>
                    <form action="${contextPath}/products/modify" method="get">
                        <td><button type="submit" class="button" name="giftid" value="${giftCard.id_prodotto}"> O </button></td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>