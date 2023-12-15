<%@ page import="model.utente.UtenteManager" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ordine.Ordine" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <title>Manage Orders</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <h1> Manage Orders </h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>ID</th>
                <th>FROM</th>
                <th>DATE</th>
                <th>TOTAL</th>
                <th>DETAILS</th>
            </tr>
            </thead>
            <tbody>
            <%  ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
                Utente u;
                UtenteManager utenteManager=new UtenteManager();
                for(Ordine ordine:ordini){
                    u=utenteManager.retrieveUtente(ordine.getId_utente());%>
            <tr>
                <td><%=ordine.getId()%> </td>
                <td><%=u.getUsername()%></td>
                <td><%=ordine.getData_acquisto()%> </td>
                <td><%=ordine.getPrezzo_totale()%></td>
                <form action="${contextPath}/orders/admininfo" method="get">
                    <th><button type="submit" class="button" name="orderid" value="<%=ordine.getId()%>">SEE</button> </th>
                </form>
            </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
