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
    <title>all users</title>
</head>
<body>
<h2>All users list</h2>
<br/>
<table>
    <tr>
        <th>No.</th>
        <th>Username</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${user.username}</td>
            <td>
                <a href = "/admin/view_user_orders/${user.username}">View user orders</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
