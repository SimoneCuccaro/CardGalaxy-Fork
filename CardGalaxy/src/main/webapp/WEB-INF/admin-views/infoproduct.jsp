<%@ page import="model.prodotto.GiftCard" %>
<%@ page import="model.prodotto.GiftCardManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admin.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/table.css">
  <title>Edit Product</title>
  <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/adminheader.jsp"%>
<%@include file="../partials/adminsidebar.jsp"%>
<main class="app">
  <div class="content align-center grid-y">
    <h1> Info Product </h1>
    <form action="${contextPath}/products/showall" method="get">
      <button type="submit" class="button add-margin-bottom">BACK</button>
    </form>
    <table class="table purple ut">
      <thead>
      <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PLATFORM</th>
        <th>DESCRIPTION</th>
        <th>PRICE</th>
        <th>AVAILABILITY</th>
        <th>PHOTO</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <% int id= Integer.parseInt(request.getParameter("giftid"));
          GiftCardManager GiftCardManager = new GiftCardManager();
        GiftCard card= GiftCardManager.retrieveGiftCardByID(id);%>
        <td><%=card.getId_prodotto()%></td>
        <td><%=card.getNome()%> </td>
        <td><%=card.getPiattaforma()%></td>
        <td><%=card.getDescrizione()%> </td>
        <td><%=card.getPrezzo()%></td>
        <td><%=card.checkisAvailable()%></td>
        <td><img src="${contextPath}/images/<%=card.getFoto()%>" width="130" height="130"></td>
      </tr>
      </tbody>
    </table>
  </div>
</main>
</body>
</html>