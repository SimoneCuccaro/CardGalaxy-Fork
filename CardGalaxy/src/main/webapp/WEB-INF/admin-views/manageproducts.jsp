<%@ page import="model.prodotto.GiftCard" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
    <title>Manage Products</title>
    <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
    <div class="content grid-y align-center">
        <c:if test="${sessionScope.addProduct==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Product added successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("addProduct"); %>
        </c:if>
        <c:if test="${sessionScope.removeProduct==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Product removed successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("removeProduct"); %>
        </c:if>
        <c:if test="${sessionScope.updateProduct==true}">
            <jsp:include page="../utils/goodAlert.jsp">
                <jsp:param name="message" value="Product updated successfully!"/>
            </jsp:include>
            <% request.getSession(false).removeAttribute("updateProduct"); %>
        </c:if>
        <h1> Manage Products</h1>
        <form action="${contextPath}/products/add" method="get">
        <button type="submit" class="button add-margin-bottom">ADD A PRODUCT</button>
        </form>
        <table class="table purple ut">
            <thead>
            <tr>
                <th>NAME</th>
                <th>PLATFORM</th>
                <th>DESCRIPTION</th>
                <th>PRICE</th>
                <th>AVAILABILITY</th>
                <th>DELETE</th>
                <th>EDIT</th>
                <th>INFO</th>
            </tr>
            </thead>
            <tbody>
            <%  ArrayList<GiftCard> giftCards= (ArrayList<GiftCard>) request.getAttribute("giftCards");
                for(GiftCard card:giftCards){
            %>
            <tr>
                <td><%=card.getNome()%> </td>
                <td><%=card.getPiattaforma()%></td>
                <td><%=card.getDescrizione()%> </td>
                <td><%=card.getPrezzo()%></td>
                <td><%=card.checkisAvailable()%></td>
                <form action="${contextPath}/products/remove" method="post" onsubmit="return confirm('Are you sure?');">
                    <td><button type="submit" class="button" name="giftid" value="<%=card.getId_prodotto()%>"> X </button></td>
                </form>
                <form action="${contextPath}/products/modify" method="get">
                    <td><button type="submit" class="button" name="giftid" value="<%=card.getId_prodotto()%>"> O </button></td>
                </form>
                <form action="${contextPath}/products/info" method="get">
                    <td><button type="submit" class="button" name="giftid" value="<%=card.getId_prodotto()%>"> I </button></td>
                </form>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>