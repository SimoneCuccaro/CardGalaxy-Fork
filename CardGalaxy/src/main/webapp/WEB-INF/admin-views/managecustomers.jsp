<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Manage Customers</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Manage Customers </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>EMAIL</th>
                <th>NAME</th>
                <th>SURNAME</th>
                <th>REMOVE ACCOUNT</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>pasquale97@gmail.com</td>
                <td>Pasquale</td>
                <td>Verdi</td>
                <td><button type="submit" class="button button-for-table">REMOVE</button></td>
            </tr>
            <tr>
                <td>ethanpayne@hotmail.uk</td>
                <td>Ethan</td>
                <td>Payne</td>
                <td><button type="submit" class="button button-for-table">REMOVE</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
