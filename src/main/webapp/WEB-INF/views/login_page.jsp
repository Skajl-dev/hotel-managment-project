<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: taras
  Date: 01.12.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
    User Name: <input type="text" name="username">
    Password: <input type="password" name="password">
    <input type="submit" value="Login">
</form:form>
</body>
</html>
