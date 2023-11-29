<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"  type="text/css" href="../../CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="../../CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="../../CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="../../CSS/login.css">
    <%@include file="../utils/head.jsp"%>
    <title>Login</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Login</h1>
        <form class="login grid-y" action="" method="">
                <span> Username : </span>
                <label for="userid">
                    <input class="larger" type="text" name="email" id="userid" placeholder="Username" required>
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
        <button type="submit" class="button add-margin-bottom"> REGISTER </button>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>
