<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" href="${contextPath}/CSS/login.css">
    <title>Homepage</title>
    <%@include file="/WEB-INF/utils/head.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/partials/siteheader.jsp"%>
<%@include file="/WEB-INF/partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <fieldset class="grid-y login">
            <legend>ERROR PAGE</legend>
            <h3>ERROR 401:Not Authenticated</h3>
            <h4>Error Message:You Are Not Authenticated</h4>
            <form method="get" action="${contextPath}/user/home">
            <button type="submit" class="button">GO BACK TO HOME</button>
            </form>
        </fieldset>
    </div>
</main>
<%@include file="/WEB-INF/partials/sitefooter.jsp"%>
</body>
</html>