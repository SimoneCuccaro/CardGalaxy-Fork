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
            <tr>
                <td>mark00</td>
                <td>Steam Card 20€</td>
                <td>Very good</td>
                <td>2023-12-09</td>
                <td><button type="submit" class="button button-for-table">X</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>