<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/help-style.css">
    <title>Help</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">Need Help?</h1>
        <div>
            <form action="" method="">
            <p class="para_index"> Describe shortly your problem here :  </p>
            <label for="objectid">
                <input class="bigger-area-object" type="text" name="object" id="objectid" placeholder="Request Object" required>
            </label>
            <p class="para_index"> Insert all your problem details here : </p>
            <label for="requestid">
               <textarea id="requestid" name="request" rows="10" cols="96" required>
               </textarea>
            </label>
                <div>
                    <button type="submit" class="glowing-btn"> SUBMIT REQUEST </button>
                </div>
            </form>
        </div>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>