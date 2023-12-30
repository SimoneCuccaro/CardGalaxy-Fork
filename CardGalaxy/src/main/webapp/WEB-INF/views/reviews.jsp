<%@ page import="java.util.ArrayList" %>
<%@ page import="model.recensione.Recensione" %>
<%@ page import="model.prodotto.GiftCard" %>
<%@ page import="model.utente.UtenteManager" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="model.recensione.RecensioneManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/index-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/userprofile.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/review.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/help-style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <%@include file="../utils/head.jsp" %>
    <title>Product Reviews</title>
</head>
<body>
<%@include file="../partials/siteheader.jsp" %>
<%@include file="../partials/sitenavbar.jsp" %>
<main>
    <% ArrayList<Recensione> recensioni = (ArrayList<Recensione>) session.getAttribute("recensioni");
        GiftCard giftCard = (GiftCard) session.getAttribute("prodotto");
        UtenteManager utenteManager = new UtenteManager();
        RecensioneManager recensioneManager = new RecensioneManager();
    %>
    <div class="content">
        <c:if test="${sessionScope.deleteString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Review deleted successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("deleteString"); %>
        </c:if>
        <c:if test="${sessionScope.addString==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Review added successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("addString"); %>
        </c:if>
        <h1 class="welcome">We love hearing from you!</h1>
        <p class="para_index">
            check customers' reviews and write your own one if you want
        </p>
        <p class="para-about para_index">
            <%=giftCard.getNome()%> <%=giftCard.getPrezzo()%>&euro; Reviews :
        </p>
        <div class="blog-wrapper">
            <%if(recensioni.size()==0){%>
            <img src="${contextPath}/photos/noreviews.png" width="550" height="200">
            <%}%>
            <% for (Recensione recensione : recensioni) { %>
            <div class="blog-card">
                <div class="card-img"><img src="${contextPath}/photos/galaxy-back.jpg">
                    <% Utente u = utenteManager.retrieveUtente(recensione.getId_utente()); %>
                    <h1><%=u.getUsername()%>
                    </h1>
                </div>
                <div class="card-details"><span><i class="fa fa-calendar"></i><%=recensione.getDatarecensione()%></span>
                </div>
                <div class="card-text"><p><%=recensione.getTesto()%>
                </p></div>
                <%
                    if (utenteSession != null) {
                        if (recensione.getId_utente() == utenteSession.getId()) {
                %>
                <form action="${contextPath}/reviews/delete" method="post">

                    <button type="submit" class="button">DELETE REVIEW</button>
                </form>
                <br>
                <%
                        }
                    }
                %>
            </div>
            <% } %>
        </div>
        <%
            if (utenteSession != null) {
                int flag = 0;
                String testo = "";
                for (Recensione recensione : recensioni) {
                    if (utenteSession.getId() == recensione.getId_utente()) {
                        flag = 1;
                        testo = recensione.getTesto();
                    }
                }
                if (flag == 1) {%>
        <form action="${contextPath}/reviews/modify" method="post">
            <p class="para_index"> change your review about this product : </p>
            <label for="reviewid1">
                <textarea id="reviewid1" name="review" rows="5" cols="96" required><%=testo%></textarea>
            </label>
            <div>
                <button type="submit" class="glowing-btn">UPDATE REVIEW</button>
            </div>
        </form>
        <%} else {%>
        <form action="${contextPath}/reviews/add" method="post">
            <p class="para_index"> write your thoughts about this product here : </p>
            <label for="reviewid">
                <textarea id="reviewid" name="review" rows="5" cols="96" required></textarea>
            </label>
            <div>
                <button type="submit" class="glowing-btn"> INSERT REVIEW</button>
            </div>
        </form>
        <%
                }
            }
        %>
    </div>
</main>
<%@include file="../partials/sitefooter.jsp" %>
</body>
</html>