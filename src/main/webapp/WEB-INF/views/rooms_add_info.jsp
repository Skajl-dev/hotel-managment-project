<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: postf
  Date: 02.12.2020
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>new rooms info</title>

    <script type="text/javascript">

        function validateData() {

    var amount = document.getElementById('amountOfRoomsInput').value;
    var name = document.getElementById('hotelInput').value;
    if (amount <= 0) {
        alert("amount should be greater than 0")
        return false;
    } else {
        if (name.length < 2 || name.length > 20) {
            alert("name should be between 2 and 20 characters")
            return false;
        }
        return true;
    }

        }

    </script>

</head>
<body>
<security:authorize access="hasRole('MANAGER')">

<div align="left">
    <h3>Add rooms to hotel:</h3>
    <form:form action="${pageContext.request.contextPath}/new_rooms" method="get" onsubmit="return validateData()">
        <c:choose>
            <c:when test="${hotelName.equals('')}">
                <label for="hotelInput">Name :</label>
                <input name="hotelName" id="hotelInput" value="${hotelName}">
            </c:when>
            <c:otherwise>
                <input name="hotelName" id="hotelInput" type="hidden" value="${hotelName}">
            </c:otherwise>
        </c:choose>

        <label for="amountOfRoomsInput">Amount :</label>
        <input name="amountOfRooms" id="amountOfRoomsInput">

        <input type="submit" value="next">
    </form:form>
</div>
</security:authorize>
</body>
</html>
