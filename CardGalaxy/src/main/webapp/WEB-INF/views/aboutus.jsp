<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <title>About Us</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
    <div class="content">
        <h1 class="welcome">The Journey Started Here</h1>
        <p class="para_index">
            We are a startup company based in Salerno, Italy.
        </p>
        <div>
            <iframe class="salerno_map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48415.39544850654!2d14.751456434241335!3d40.67480147517453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bc25734bd3089%3A0x15a49713da0374a0!2sSalerno%20SA!5e0!3m2!1sit!2sit!4v1699699125794!5m2!1sit!2sit" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <p class="para_index para-about">
            The mission is to build on our passion for the digital world.
        </p>
        <img class="digital-img" src="${contextPath}/photos/digital-world.jpeg" alt="digital world">
        <h2 class="about-heading add-margin-top">
            We want to let you choose your gift cards between multiple platforms.
        </h2>
        <p class="para_index para-about add-margin-bottom">
            You won't spend hours searching anymore. We promise.
        </p>
        <h2 class="about-heading add-margin-bottom">
            Explore the galaxy with us!
        </h2>
        <h2 class="about-heading purplish add-margin-bottom">
            Join the community now!
        </h2>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>
