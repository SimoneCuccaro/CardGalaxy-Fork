<%@ page import="model.richiestasupporto.RichiestaSupporto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="model.utente.UtenteManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Help Requests</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <c:if test="${sessionScope.responseDone==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Response sended successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("responseDone"); %>
        </c:if>
        <h1> Help Requests </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>FROM</th>
                <th>OBJECT</th>
                <th>DETAILS</th>
                <th>ANSWER</th>
            </tr>
            </thead>
            <tbody>
            <%  ArrayList<RichiestaSupporto> richieste= (ArrayList<RichiestaSupporto>) request.getAttribute("richieste");
                Utente u;
                UtenteManager utenteManager=new UtenteManager();
                for(RichiestaSupporto richiesta:richieste){
                    u=utenteManager.retrieveUtente(richiesta.getId_utente());%>
            <tr>
                <td><%=u.getUsername()%> </td>
                <td><%=richiesta.getOggetto_richiesta()%></td>
                <td><%=richiesta.getRichiesta()%> </td>
                <form action="${contextPath}/response/create" method="get">
                    <td><button type="submit" class="button" name="requestid" value="<%=richiesta.getId_richiesta()%>"> ANSWER </button></td>
                    <input type="hidden" name="userid" value="<%=u.getId()%>">
                </form>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>