<%@ page import="java.util.List" %>
<%@ page import="com.example.proiect_frontend.dto.MovieDto" %>
<%@ page import="com.example.proiect_frontend.MoviesRestClient" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Movies</title>
</head>
<body>
<%
    MoviesRestClient brc = new MoviesRestClient();
    List<MovieDto> movies = brc.getMovies();
%>
Movies
<form action="/proiect_frontend-1.0-SNAPSHOT/filteredMovies.jsp" method="POST">
        <input type="text" name="input">
        <input type="submit" name="form" value="Search">
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Actors</th>
        <th>Year</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for(MovieDto b: movies) { %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getActor() %></td>
        <td><%= b.getYear() %></td>
        <td>
            <a href="updateMovie.jsp?id=<%=b.getId()%>">Edit</a>
            <a href="deleteMovie.jsp?id=<%=b.getId()%>">Delete</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="createMovie.jsp">New</a>
</body>
</html>
