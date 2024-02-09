<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <%@include file="../utils/head.jsp"%>
    <title>Login</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <c:if test="${sessionScope.deleteString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="User deleted successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("deleteString"); %>
        </c:if>
        <c:if test="${sessionScope.helpString==true}">
            <jsp:include page="../utils/infoAlert.jsp">
                <jsp:param name="message" value="You need to be logged in to make an help request!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("helpString"); %>
        </c:if>
        <h1 class="welcome">Login</h1>
        <form class="login grid-y" action="${contextPath}/user/signin" method="post">
            <c:if test="${not empty alert}">
                <%@include file="../utils/alert.jsp" %>
            </c:if>
                <span> Username : </span>
                <label for="userid">
                    <input class="larger" type="text" name="user" id="userid" placeholder="Username" required>
                </label>
            <br>
                <span> Password : </span>
                <label for="passwordid">
                    <input class="larger" type="password" name="password" id="passwordid" placeholder="Password" required>
                </label>
                <br>
                <button class="button" type="submit"> ENTER </button>
                <br>
        </form>
        <p class="para_index">
            Don't have an account?
        </p>
        <form action="${contextPath}/user/register" method="get">
        <button type="submit" class="button add-margin-bottom"> REGISTER </button>
        </form>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>
