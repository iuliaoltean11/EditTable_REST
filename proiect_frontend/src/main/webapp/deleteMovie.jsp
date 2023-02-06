<%@ page import="com.example.proiect_frontend.MoviesRestClient" %>
<%@ page import="com.example.proiect_frontend.dto.MovieDto" %><%--
  Created by IntelliJ IDEA.
  User: iulia
  Date: 12/11/2022
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete movie</title>
</head>
<body>
<%
    // aduc un film baza pe un id luat din request din url
    MoviesRestClient mrc = new MoviesRestClient();
    String movieId = request.getParameter("id");
    Integer id = Integer.parseInt(movieId);
    MovieDto movie= mrc.getMovieById(id);
%>
<% if(movie != null) {%>
Are you sure you want to delete movie <%=movie.getTitle()%>?
<form action="actionservlet" method="POST">
    <input type="hidden" name="action" value="deleteMovie">
    <input type="hidden" name="id" value="<%=movie.getId()%>" >
<%--    aici tot ce se vede e un cancel si un delete--%>
    <a href="<%=request.getContextPath()%>/index.jsp">Cancel</a>
    <input type="submit" value="Delete">
</form>
<% } else {%>
<%--daca nu exista filmul are doar un buton spre home--%>
<a href="<%=request.getContextPath()%>/index.jsp">Home</a>
<% } %>
</body>
</html>

