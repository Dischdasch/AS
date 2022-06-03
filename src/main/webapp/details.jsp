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
<div class="content">
 <b>Details for</b> <%out.print(request.getParameter("movie")); %>
    <form method="GET" action="FrontControllerServlet">
        <input type="text" name="command" value="addToCart"/>
        <input type="text" name="movie" value=<%= request.getParameter("movie")%> />
        <button type="submit">Add To Card</button>
    </form>
</div>
</body>
</html>