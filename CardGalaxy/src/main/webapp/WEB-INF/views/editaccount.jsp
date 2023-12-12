<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet"  type="text/css" href="${contextPath}/CSS/index-style.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/aboutus-style.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/support-button.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/alert.css">
  <title>Edit Account</title>
  <%@include file="../utils/head.jsp"%>
</head>
<body>
<%@include file="../partials/siteheader.jsp"%>
<%@include file="../partials/sitenavbar.jsp"%>
<main>
  <div class="content">
    <h1 class="welcome">Edit Your Account</h1>
    <form class="login grid-y" action="${contextPath}/user/update" method="post" onsubmit="return confirm('Are you sure?');">
      <c:if test="${not empty alert}">
        <%@include file="../utils/alert.jsp" %>
      </c:if>
      <span> Email : </span>
      <label for="emailid">
        <input class="larger" type="email" name="email" id="emailid" value="${user.email}"  required>
      </label>
      <br>
      <span> Username : </span>
      <label for="userid">
        <input class="larger" type="text" name="user" id="userid" value="${user.username}" required>
      </label>
      <br>
      <span> Password : </span>
      <label for="passwordid">
        <input class="larger" type="password" name="password" id="passwordid" placeholder="Password" required>
      </label>
      <br>
      <span> Name : </span>
      <label for="nameid">
        <input class="larger" type="text" name="name" id="nameid" value="${user.nome}" required>
      </label>
      <br>
      <span> Surname : </span>
      <label for="surnameid">
        <input class="larger" type="text" name="surname" id="surnameid" value="${user.cognome}" required>
      </label>
      <br>
      <span> Address : </span>
      <label for="addressid">
        <input class="larger" type="text" name="address" id="addressid" value="${user.indirizzo}" required>
      </label>
      <br>
      <span> Nation : </span>
      <label for="nationid">
        <input class="larger" type="text" name="nation" id="nationid" value="${user.nazione}" required>
      </label>
      <br>
      <span> City : </span>
      <label for="cityid">
        <input class="larger" type="text" name="city" id="cityid" value="${user.citta}" required>
      </label>
      <br>
      <span> CAP : </span>
      <label for="capid">
        <input class="larger" type="number" name="cap" id="capid" value="${user.cap}" required>
      </label>
      <br>
      <span> Date of Birth : </span>
      <label for="bdayid">
        <input class="larger" type="date" min="1923-01-01" max="2005-01-01" name="bday" id="bdayid" value="${user.data_nascita}" required>
      </label>
      <br>
      <button class="button" type="submit"> EDIT DETAILS </button>
      <br>
    </form>
  </div>
</main>
<%@include file="../partials/sitefooter.jsp"%>
</body>
</html>
