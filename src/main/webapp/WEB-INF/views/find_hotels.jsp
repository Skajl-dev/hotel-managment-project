<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 28.11.2020
  Time: 23:03
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
<section class="section">
    <div class="container">
        <div class="content">

            <table>
                <tr>
                    <td>name</td>
                    <td>country</td>
                    <td>Available rooms</td>
                </tr>
                <c:forEach var="hotel" items="${hotels}">
                    <c:url var="getRoomsLink" value="/rooms">
                        <c:param name="hotelName" value="${hotel.name}"/>
                    </c:url>
                    <tr>
                        <td>${hotel.name}</td>
                        <td>${hotel.country}</td>
                        <td>
                            <form:form action="${getRoomsLink}" method="post">
                                <input name="startDate" type="date" placeholder="From...">
                                <input name="endDate" type="date" placeholder="To...">
                                <input type="submit" value="Get rooms">
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form action="${pageContext.request.contextPath}/back_to_start">
                <input type="submit" value="Back Home">
            </form>
        </div>
    </div>
</section>

</body>
</html>
