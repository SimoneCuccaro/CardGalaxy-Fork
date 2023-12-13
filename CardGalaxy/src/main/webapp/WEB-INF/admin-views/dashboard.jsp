<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/statscard.css">
    <title>Admin Dashboard</title>
    <%@include file="../utils/head.jsp"%>
    <script>
        function alertUser(msg) {
            alert(msg);
        }
    </script>
</head>
<%
    Boolean done = (Boolean) session.getAttribute("done");
    if(done){
%>
<body onload="alertUser('Welcome!')"
<%
    }
    session.setAttribute("done",false);
%>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content align-center">
        <h1> General Stats </h1>
        <div class="cards">
            <div class="card">
                <div class="container-card">
                    <img src="${contextPath}/photos/profit.png" alt="profit icon" class="card-icon">
                    <p class="card-title">
                        Galaxy Profits
                    </p>
                    <p class="card-description">
                        ${countProfits}&euro;
                    </p>
                </div>
            </div>
        </div>
        <div class="cards">
            <div class="card">
                <div class="container-card">
                    <img src="${contextPath}/photos/icon-customer.png" alt="customers icon" class="card-icon">
                    <p class="card-title">
                        Galaxy Customers
                    </p>
                    <p class="card-description">
                        ${countUsers}
                    </p>
                </div>
            </div>
        </div>
        <div class="cards">
            <div class="card">
                <div class="container-card">
                    <img src="${contextPath}/photos/card-icon.png" alt="customers icon" class="card-icon">
                    <p class="card-title">
                        Galaxy Cards
                    </p>
                    <p class="card-description">
                        ${countCards}
                    </p>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
