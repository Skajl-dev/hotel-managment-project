<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 28.11.2020
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="section">
    <div class="container">
        <div class="content">

            <table>
                <tr>
                    <td>id</td>
                    <td>name</td>
                    <td>country</td>
                    <td>Available rooms</td>
                </tr>
                <c:forEach var="hotel" items="${hotels}">
                    <c:url var="getRoomsLink" value="/rooms">
                        <c:param name="hotelName" value="${hotel.name}"/>
                    </c:url>
                    <tr>
                        <td>${hotel.id}</td>
                        <td>${hotel.name}</td>
                        <td>${hotel.country}</td>
                        <td>
                            <a href="${getRoomsLink}">rooms</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

</body>
</html>
