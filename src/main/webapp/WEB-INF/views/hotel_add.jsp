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
<div align="left">

    <h3>New Hotel</h3>
    <form:form action="/admin/save_hotel" method="post" modelAttribute="hotel">

    <form:hidden path="id"/>

        <p>Country:</p>
        <form:input path="country"/>
        <form:errors path="country"/>

        </p>Hotel name:</p>
        <form:input path="name"/>
        <form:errors path="name"/>
    <hr/>
    <br/>
    <br/>

       <input type="submit" value="Save">


    </form:form>

</div>
</body>
</html>
