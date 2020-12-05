<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: postf
  Date: 05.12.2020
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Orders for user ${username}</h3>
<br/>
<table cellpadding="5" border="1">
    <tr>
        <th>Order id</th>
        <th>Date of booking</th>
        <th>Hotel name</th>
        <th>Room №</th>
    </tr>


<c:forEach var="order" items="${orders}">
    <tr>
    <td>${order.id}</td>
    <td>${order.dateOfBooking}</td>
    <td>${order.room.hotel.name}</td>
    <td>${order.room.name}</td>
    </tr>
</c:forEach>

</table>
<br/>

<form method="get" action="/back_to_start">
    <button type="submit">back to main</button>
</form>

</body>
</html>
