<%@ page import="com.example.moviegf6.MovieAccessor" %>
<%@ page import="com.example.moviegf6.MovieEntity" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<%
    MovieAccessor movieA = new MovieAccessor();
    MovieEntity movie = movieA.getMovie(Integer.parseInt(request.getParameter("movieId")));%>

<div class="content">
    <b>Details for</b><%= movie.getName()%>
    <p>${movie.name}</p>
    <p>${movie.description}</p>
    <form method="GET" action="FrontControllerServlet">
        <input type="text" name="command" value="addToCart"/>
        <input type="text" name="movie" value=<%= request.getParameter("movieId")%> />
        <button type="submit">Add To Card</button>
    </form>
    <form method="GET" action="FrontControllerServlet">
        <input type="hidden" name="delete" value=<%= request.getParameter("movieId")%> />
        <input type="hidden" name="command" value="deleteCommand" />
        <button type="submit">Delete this Movie</button>
    </form>
</div>
</body>
</html>