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
            <tr>
                <td>1</td>
                <td>mark00</td>
                <td>2023-12-14</td>
                <td>400&euro;</td>
                <form action="${contextPath}/orders/admininfo" method="get">
                <th><button type="submit" class="button">SEE</button> </th>
                    <input type="hidden" name="customerid" value="">
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
