<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Order Details</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Order Details </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>PRODUCT</th>
                <th>PRICE</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>PSCard</td>
                <td>20&euro;</td>
            </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
