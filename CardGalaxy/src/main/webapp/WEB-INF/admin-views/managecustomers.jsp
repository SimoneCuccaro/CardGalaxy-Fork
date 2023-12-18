<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %><%
    ArrayList<Utente> customers = (ArrayList<Utente>) request.getAttribute("customers");
%>
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
        <c:if test="${sessionScope.removeString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="User removed successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("removeString"); %>
        </c:if>
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
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.email}</td>
                    <td>${customer.nome}</td>
                    <td>${customer.cognome}</td>
                    <form action="${contextPath}/user/remove" method="post" onsubmit="return confirm('Are you sure?');">
                    <td><button type="submit" class="button" name="customerid" value="${customer.id}"> X </button></td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
