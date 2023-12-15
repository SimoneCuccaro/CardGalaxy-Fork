<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <%@include file="../utils/head.jsp"%>
    <title>Order History</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
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
            <tr>
                <td>1</td>
                <td>20/11/2023</td>
                <td>50€</td>
                <form action="${contextPath}/orders/info" method="get">
                <td><button type="submit" class="button">VIEW</button> </td>
                </form>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>