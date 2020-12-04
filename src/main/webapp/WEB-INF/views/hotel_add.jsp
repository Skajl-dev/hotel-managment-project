<%--
  Created by IntelliJ IDEA.
  User: postf
  Date: 02.12.2020
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>new hotel</title>
</head>
<body>
<div align="center">
<security:authorize access="hasRole('MANAGER')">
    <h3>New Hotel</h3>
    <form:form action="save_hotel" method="post" modelAttribute="hotel">
<table>
    <form:hidden path="id"/>
    <tr>
        <td>Country: </td>
        <td><form:input path="country"/></td>
       <td> <form:errors path="country"/> </td>
    </tr>
    <tr>
        <td>Hotel name: </td>
        <td><form:input path="name"/></td>
        <td> <form:errors path="name"/> </td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Save"></td>
    </tr>
</table>
    </form:form>
</security:authorize>
</div>
</body>
</html>
