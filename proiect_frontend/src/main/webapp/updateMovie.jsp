<%--
  Created by IntelliJ IDEA.
  User: iulia
  Date: 12/11/2022
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proiect_frontend.MoviesRestClient" %>
<%@ page import="com.example.proiect_frontend.dto.MovieDto" %>
<html>
<head>
    <title>Edit Movie</title>
</head>
<body>
<%
    MoviesRestClient brc = new MoviesRestClient();
    String movieId = request.getParameter("id");
    Integer id = Integer.parseInt(movieId);
    MovieDto movie = brc.getMovieById(id);
%>
Update Movie
<form action="actionservlet" method="POST">
    <input type="hidden" name="action" value="updateMovie">
    <br> Id: <input type="text" name="id" value="<%=movie.getId()%>" readonly>
    <br> Title: <input type="text" name="title" value="<%=movie.getTitle()%>">
    <br> Actor: <input type="text" name="actor" value="<%=movie.getActor()%>">
    <br> Year: <input type="text" name="year" value="<%=movie.getYear()%>">
    <br>
<%--    dupa modificare se trimite cu butonul de update pe actionservlet--%>
    <input type="submit" value="Update">
</form>
</body>
</html>