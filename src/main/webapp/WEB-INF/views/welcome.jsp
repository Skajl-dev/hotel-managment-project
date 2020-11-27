<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 26.11.2020
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello</h1>
</body>
</html>
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Travel agency Home</title>
</head>
<body>

<div align="center">
    <h1>Contact List</h1>
    <table border="1" cellpadding="5">
            <tr>
                <th>№</th>
                <th>Hotel name</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
        <c:forEach items="${listHotel}" var="hotel" varStatus="status">
    <tr>
        <td>${status.index + 1}</td>
        <td>${hotel.name}</td>
        <td>${hotel.country}</td>
    </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>