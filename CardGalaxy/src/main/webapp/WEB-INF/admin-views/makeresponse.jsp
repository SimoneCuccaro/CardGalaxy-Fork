<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">

    <title>Manage Customers</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <c:if test="${not empty alert}">
            <%@include file="../utils/alert.jsp" %>
        </c:if>
        <h1> Response to user: ${sessionScope.user_username} </h1>
        <h2> and his request: ${sessionScope.request_object} </h2>
        <form action="${contextPath}/response/submit" method="post">
            <label for="responseid">
               <textarea id="responseid" name="response" rows="10" cols="96" required></textarea>
            </label>
            <input type="hidden" name="userid" value="${sessionScope.userid}">
            <input type="hidden" name="requestid" value="${sessionScope.requestid}">
            <div>
                <button type="submit" class="button add-margin-bottom"> SUBMIT RESPONSE </button>
            </div>
        </form>
    </div>
</main>
</body>
</html>
