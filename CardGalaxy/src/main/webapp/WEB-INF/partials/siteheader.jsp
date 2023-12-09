<%@ page import="model.utente.UtenteSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath",contextPath);
    UtenteSession utenteSession = (UtenteSession) session.getAttribute("utenteSession");
%>
<header>
    <div class="user-svg">
        <%
            if(session.getAttribute("utenteSession") == null){
        %>
        <a href="${contextPath}/user/login"><%@include file="../../icons/user-solid.svg"%></a>
        <%
            }
            else {
        %>
        <a href="${contextPath}/user/profile"><%@include file="../../icons/user-solid.svg"%></a>
        <h2 class="hi">Hi <%=utenteSession.getNome()%>!</h2>
        <%
            }
        %>
    </div>
    <div class="cart-svg">
        <a href="${contextPath}/cart/show"><%@include file="../../icons/cart.svg"%></a>
        <span class="badge">0</span>
    </div>
    <img id="logo" src="${contextPath}/photos/cardgalaxylogo.png" alt="website logo">
</header>
