<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Help Requests</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Help Requests </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>FROM</th>
                <th>OBJECT</th>
                <th>DETAILS</th>
                <th>ANSWER</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${richieste}" var="request">
                <tr>
                    <td>${request.id_utente}</td>
                    <td>${request.oggetto_richiesta}</td>
                    <td>${request.richiesta}</td>
                    <form action="${contextPath}/response/create" method="get">
                        <td><button type="submit" class="button" name="requestid" value="${request.id_richiesta}"> Answer </button></td>
                        <input type="hidden" name="userid" value="${request.id_utente}">
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>