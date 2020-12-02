<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 29.11.2020
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<p>
    User: <security:authentication property="principal.username"/>
</p>
<p>
    Status: <security:authentication property="principal.authorities"/>
</p>
<br>
<p>Availible actions:</p>
<form:form action="${pageContext.request.contextPath}/find_hotels" method="get">
    <label for="countryInput">Find hotel by country:</label>
    <input name="countryName" id="countryInput">
    <input type="submit" value="Find">
</form:form>

<security:authorize access="hasRole('MANAGER')">
    <p><a href="#">add hotels in the system</a></p>
    <p><a href="#">add rooms to the hotel</a></p>
    <p><a href="#">view all users and their orders</a></p>
</security:authorize>

<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form:form>
</body>
</html>
