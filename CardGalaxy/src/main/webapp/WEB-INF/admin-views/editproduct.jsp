<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Edit Product</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content align-center">
        <c:if test="${not empty alert}">
            <%@include file="../utils/alert.jsp" %>
        </c:if>
        <h1> Edit Product </h1>
        <form class="login grid-y" action="${contextPath}/products/update" method="post" enctype="multipart/form-data">
            <span> Name: </span>
            <label for="nameid">
                <input class="larger" type="text" name="name" id="nameid" value="${giftCard.nome}" required>
            </label>
            <br>
            <span> Platform : </span>
            <label for="platformid">
                <input class="larger" type="text" name="platform" id="platformid" value="${giftCard.piattaforma}" required>
            </label>
            <br>
            <span> Description : </span>
            <label for="descriptionid">
                <input class="larger" type="text" name="description" id="descriptionid" value="${giftCard.descrizione}" required>
            </label>
            <br>
            <span> Price : </span>
            <label for="priceid">
                <input class="larger" type="number" name="price" id="priceid" value="${giftCard.prezzo}" required>
            </label>
            <br>
            <span> Photo : </span>
            <label for="photoid">
                <input class="larger" type="file" name="photo" id="photoid"  required>
            </label>
            <br>
            <span> Availability : </span>
            <label for="availabilityidtrue">
                <input type="radio" id="availabilityidtrue" name="availability" value="true">True
            </label>
            <label for="availabilityidfalse">
                <input type="radio" id="availabilityidfalse" name="availability" value="false">False
            </label>
            <br>
            <input type="hidden" name="giftid" value="${giftCard.id_prodotto}">
            <button type="submit" class="button"> SAVE CHANGES </button>
            <br>
        </form>
    </div>
</main>
</body>
</html>