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
            <tr>
                <td>1</td>
                <td>Steam Card</td>
                <td>Steam Inc.</td>
                <td>Earn 20€ to buy some content on Steam!</td>
                <td>20€</td>
                <td><button type="submit" class="button button-for-table">X</button></td>
                <td><button type="submit" class="button button-for-table">O</button></td>
            </tr>
            <tr>
                <td>2</td>
                <td>PlayStation Card</td>
                <td>PlayStation Inc.</td>
                <td>Earn 50€ to buy some content on the PS Store!!</td>
                <td>50€</td>
                <td><button type="submit" class="button button-for-table">X</button></td>
                <td><button type="submit" class="button button-for-table">O</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>