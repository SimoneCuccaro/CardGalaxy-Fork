<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/statscard.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Admin Dashboard</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content align-center">
        <c:if test="${sessionScope.adminString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Welcome Admin"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("adminString"); %>
        </c:if>
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
