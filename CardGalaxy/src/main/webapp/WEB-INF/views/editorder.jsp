<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <title>Edit Order</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Edit Your Order</h1>
        <form class="login grid-y" action="" method="">
            <span> PlayStation Card 20€ Quantity : </span>
            <label for="quantityid">
                <input class="larger" type="number" min="1" max="10" name="email" id="quantityid" placeholder="" required>
            </label>
            <br>
            <button class="button" type="submit"> EDIT ORDER </button>
            <button class="button" type="submit"> ADD MORE CARDS </button>
            <br>
        </form>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>