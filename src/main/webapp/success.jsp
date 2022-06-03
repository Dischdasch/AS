<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Welcome Page</title>
  <link href="css/Success.css" rel="stylesheet"type="text/css" >
</head>
<body>
<!-- header -->
<jsp:include page="header.jsp" />
<div class="content">
  <%  String name=request.getParameter("username"); %>

  <b>Welcome,</b> <% out.print(name); %>
  <h4>Movies Available</h4>
  <form method="GET" action="FrontControllerServlet">
    <table border="1">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <c:forEach items="${allMovies}" var="movie">
        <tr>
          <td><input onChange="this.form.submit()" type="checkbox" name ="movieId" value =${fn:escapeXml(movie.id)} id=${fn:escapeXml(movie.id)} /></td>
          <td>${fn:escapeXml(movie.id)}</td>
          <td>${fn:escapeXml(movie.name)}</td>
          <td>${fn:escapeXml(movie.description)}</td>
        </tr>
      </c:forEach>
    </table>
    <input type="hidden" name="command" value="details"/>
  </form>
</div>

<nav aria-label="Navigation for countries">
  <ul class="pagination">
    <c:if test="${currentPage != 1}">
      <li class="page-item"><a class="page-link"
                               href="ReadCountries?recordsPerPage=${recordsPerPage}¤tPage=${currentPage-1}">Previous</a>
      </li>
    </c:if>

    <c:forEach begin="1" end="${pageNumber}" var="i">
      <c:choose>
        <c:when test="${currentPage eq i}">
          <li class="page-item active"><a class="page-link">
              ${i} <span class="sr-only">(current)</span></a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item"><a class="page-link"
                                   href="ReadCountries?recordsPerPage=${recordsPerPage}¤tPage=${i}">${i}</a>
          </li>
        </c:otherwise>
      </c:choose>
    </c:forEach>

    <c:if test="${currentPage lt pageNumber}">
      <li class="page-item"><a class="page-link"
                               href="ReadCountries?recordsPerPage=${recordsPerPage}¤tPage=${currentPage+1}">Next</a>
      </li>
    </c:if>
  </ul>
</nav>

</body>
</html>