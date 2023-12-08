<div class="notification ${alert.type}">
    <ul class="cell">
        <c:forEach var="msg" items="${alert.messages}">
            <li>${msg}</li>
        </c:forEach>
    </ul>
    <span id="notification-close" class="close" onclick="this.parentNode.remove(); return false;">
<%@include file="../../icons/x.svg"%>
</span>
</div>