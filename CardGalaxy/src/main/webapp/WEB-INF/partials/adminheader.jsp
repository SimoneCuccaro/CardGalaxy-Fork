<%@ page import="model.utente.UtenteSession" %>
<%
    UtenteSession utenteSession = (UtenteSession) session.getAttribute("utenteSession");
%>
<header class="grid-y align-center">
    <h1>-Welcome <%=utenteSession.getNome()%>-</h1>
</header>