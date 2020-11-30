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
                <td>Make book a hotel room</td>
            </tr>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room}</td>
                    <td>
                        <form action="/book_room" method="post">
                            <input type="date" name="bookingDate">
                            <input type = "submit" value = "Book" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
