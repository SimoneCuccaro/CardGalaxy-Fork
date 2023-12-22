<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Your Profile</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <c:if test="${sessionScope.loginString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Welcome User!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("loginString"); %>
        </c:if>
        <c:if test="${sessionScope.registerString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Registration done successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("registerString"); %>
        </c:if>
        <c:if test="${sessionScope.updateString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Update done successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("updateString"); %>
        </c:if>
        <h1 class="welcome">Welcome <%=utenteSession.getUsername()%> </h1>
        <div>
        <img src="${contextPath}/photos/profile.png" alt="profile pic" class="smiley-pc">
        </div>
        <form action="${contextPath}/user/details" method="get">
        <button type="submit" class="glowing-btn"> VIEW ACCOUNT DETAILS </button>
        </form>
        <form action="${contextPath}/orders/showall" method="get">
        <button type="submit" class="glowing-btn"> VIEW ORDERS HISTORY </button>
        </form>
        <form action="${contextPath}/user/logout" method="post">
        <button type="submit" class="glowing-btn"> LOGOUT </button>
        </form>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>