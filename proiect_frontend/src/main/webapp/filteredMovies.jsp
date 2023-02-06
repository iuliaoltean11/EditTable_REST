<%@ page import="java.util.List" %>
<%@ page import="com.example.proiect_frontend.dto.MovieDto" %>
<%@ page import="com.example.proiect_frontend.MoviesRestClient" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Movies</title>
</head>
<body>
Movies
<%
    MoviesRestClient moviesRestClient = new MoviesRestClient();
    List<MovieDto> filteredList = new ArrayList<>();
    List<MovieDto> movies = moviesRestClient.getMovies();
    String text = request.getParameter("input"); // ia param cu numele de input, ia ce pun in cautare
    for (MovieDto movie: movies
         ) {
        if (movie.getTitle().toLowerCase().contains(text.toLowerCase()) || movie.getActor().toLowerCase().contains(text.toLowerCase())) {
            filteredList.add(movie);
        }
    }
%>
<h1></h1>
<%--un form trimite la filteredMovies cu Post--%>
<form action="/proiect_frontend-1.0-SNAPSHOT/filteredMovies.jsp" method="POST">
    <input type="text" name="input">
    <input type="submit" value="Search">
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
    <% for(MovieDto b: filteredList) { %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getActor() %></td>
        <td><%= b.getYear() %></td>
        <td>
<%--            trimite catre jsp apelul--%>
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
