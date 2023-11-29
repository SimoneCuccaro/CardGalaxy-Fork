<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath",contextPath);
%>
<header>
    <div class="user-svg">
        <a href="${contextPath}/user/login"><%@include file="../../icons/user-solid.svg"%></a>
    </div>
    <div class="cart-svg">
        <%@include file="../../icons/cart.svg"%>
        <span class="badge">0</span>
    </div>
    <img id="logo" src="${contextPath}/photos/cardgalaxylogo.png" alt="website logo">
</header>
