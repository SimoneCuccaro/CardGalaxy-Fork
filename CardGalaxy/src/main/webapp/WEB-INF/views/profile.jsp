<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <title>Your Profile</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Welcome <%=utenteSession.getUsername()%> </h1>
        <div>
        <img src="${contextPath}/photos/profile.png" alt="profile pic" class="smiley-pc">
        </div>
        <form action="${contextPath}/user/details" method="get">
        <button type="submit" class="glowing-btn"> VIEW ACCOUNT DETAILS </button>
        </form>
        <button type="submit" class="glowing-btn"> VIEW ORDERS HISTORY </button>
        <form action="${contextPath}/user/logout" method="post">
        <button type="submit" class="glowing-btn"> LOGOUT </button>
        </form>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>