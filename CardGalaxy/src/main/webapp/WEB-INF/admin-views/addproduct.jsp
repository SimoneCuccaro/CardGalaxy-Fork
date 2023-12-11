<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <title>Add Product</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content align-center">
        <h1> Add Product </h1>
        <form class="login grid-y" action="" method="">
            <span> Name: </span>
            <label for="nameid">
                <input class="larger" type="text" name="name" id="nameid" placeholder="XBOX Card" required>
            </label>
            <br>
            <span> Platform : </span>
            <label for="platformid">
                <input class="larger" type="text" name="platform" id="platformid" placeholder="XBOX" required>
            </label>
            <br>
            <span> Description : </span>
            <label for="descriptionid">
                <input class="larger" type="text" name="description" id="descriptionid" placeholder="..." required>
            </label>
            <br>
            <span> Price : </span>
            <label for="priceid">
                <input class="larger" type="number" name="price" id="priceid" placeholder="50" required>
            </label>
            <br>
            <button type="submit" class="button"> ADD TO PRODUCTS </button>
            <br>
        </form>
    </div>
</main>
</body>
</html>