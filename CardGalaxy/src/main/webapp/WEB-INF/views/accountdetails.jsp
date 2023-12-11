<%@ page import="model.utente.Utente" %><%
    Utente u = (Utente) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <title>Account Details</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Account Details</h1>
        <p class="para-about para_index">
            Name : <%=u.getNome()%>
            <br>
            Surname : <%=u.getCognome()%>
            <br>
            Username : <%=u.getUsername()%>
            <br>
            Email : <%=u.getEmail()%>
            <br>
            Address : <%=u.getIndirizzo()%>
            <br>
            Nation : <%=u.getNazione()%>
            <br>
            City : <%=u.getCitta()%>
            <br>
            CAP : <%=u.getCap()%>
            <br>
            Date of Birth : <%=u.getData_nascita()%>
        </p>
        <form action="${contextPath}/user/modify" method="get">
             <button type="submit" class="glowing-btn"> EDIT DETAILS </button>
        </form>
        <form action="${contextPath}/user/delete" method="post" onsubmit="return confirm('Are you sure?');">
            <button type="submit" class="glowing-btn"> DELETE ACCOUNT </button>
        </form>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>