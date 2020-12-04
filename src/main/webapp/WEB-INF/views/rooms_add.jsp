<%--
  Created by IntelliJ IDEA.
  User: postf
  Date: 02.12.2020
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>new rooms</title>


</head>
<body>
<security:authorize access="hasRole('MANAGER')">
<h3>Set rooms names: </h3>

<form:form action="${pageContext.request.contextPath}/save_rooms" method="post" modelAttribute="roomForm">

    <input type="hidden" name="hotelName" id="hotelInput" value="${hotelName}">

    <table>
        <tr>
            <th>No.</th>
            <th>Name</th>
        </tr>

        <c:forEach var="room" items="${roomForm.rooms}" varStatus="status">

            <tr>
                <td>${status.count}</td>
                <td><input type="text" name="rooms[${status.index}].name" id="Room№${status.count}"></td>
            </tr>

        </c:forEach>

    </table>
    <hr/>
    <br/>
    <br/>
    <input type="submit" value="Submit">
</form:form>

</security:authorize>
</body>
</html>
