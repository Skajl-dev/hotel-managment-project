<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 29.11.2020
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="content">
        <table>
            <tr>
                <td>Room name</td>
                <td>Book a hotel room</td>
            </tr>
            <c:forEach var="room" items="${rooms}">
                <c:url var="getRoomLink" value="/book_room">
                    <c:param name="roomId" value="${room.id}"/>
                </c:url>
                <tr>
                    <td>${room.name}</td>
                    <td>
                        <form:form action="${getRoomLink}" method="post">
                            <select name="bookingDate">
                                <c:forEach var="date" items="${dates}">
                                     <option value="${date}">${date}</option>
                                </c:forEach>
                            </select>
                            <input type = "submit" value = "Book" />
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
