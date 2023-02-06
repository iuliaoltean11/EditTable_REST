<%--
  Created by IntelliJ IDEA.
  User: iulia
  Date: 12/11/2022
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%--setez limbajul--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Movie</title>
</head>
<body>

Add new Movie
<form action="actionservlet" method="POST">
    <input type="hidden" name="action" value="createMovie">
    <br> Title: <input type="text" name="title">
    <br> Actor: <input type="text" name="actor">
    <br> Year: <input type="text" name="year">
    <br>
    <input type="submit" value="Create">
<%--    ce e sus e buton--%>
</form>
<%--returneaza baza linkului--%>
<br> <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
</body>
</html>
