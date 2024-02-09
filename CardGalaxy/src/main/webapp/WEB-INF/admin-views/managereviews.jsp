<%@ page import="java.util.ArrayList" %>
<%@ page import="model.prodotto.GiftCard" %>
<%@ page import="model.recensione.Recensione" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="model.utente.UtenteManager" %>
<%@ page import="model.prodotto.GiftCardManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Manage Reviews</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <c:if test="${sessionScope.removeString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Review removed successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("removeString"); %>
        </c:if>
        <h1> Manage Reviews</h1>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>FROM</th>
                <th>PRODUCT</th>
                <th>TEXT</th>
                <th>DATE</th>
                <th>REMOVE</th>
            </tr>
            </thead>
            <tbody>
            <%  ArrayList<Recensione> recensioni= (ArrayList<Recensione>) request.getAttribute("reviews");
                Utente u;
                UtenteManager utenteManager=new UtenteManager();
                GiftCard prodotto;
                GiftCardManager giftCardManager=new GiftCardManager();
                for(Recensione recensione:recensioni){
                    u=utenteManager.retrieveUtente(recensione.getId_utente());
                    prodotto=giftCardManager.retrieveGiftCardByID(recensione.getId_prodotto());%>
            <tr>
                <td><%=u.getUsername()%></td>
                <td><%=prodotto.getNome()%></td>
                <td><%=recensione.getTesto()%> </td>
                <td><%=recensione.getDatarecensione()%></td>
                <form action="${contextPath}/reviews/remove" method="post" onsubmit="return confirm('Are you sure?');">
                    <td><button type="submit" class="button" name="giftid" value="<%=prodotto.getId_prodotto()%>"> X </button></td>
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