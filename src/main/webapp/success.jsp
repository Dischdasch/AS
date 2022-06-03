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
				<ul>
  <li><input onChange="this.form.submit()" type="checkbox" name ="movie" value ="1" id="1" />
    <label for="1"><img src="images/Back.jpg" /></label>
  </li>
  <li><input onChange="this.form.submit()" type="checkbox" name ="movie" value ="2" id="2" />
    <label for="2"><img src="images/Freedom.jpg" /></label>
  </li>
  <li><input onChange="this.form.submit()" type="checkbox" name ="movie" value ="3" id="3" />
    <label for="3"><img src="images/Joker.jpg" /></label>
  </li>
</ul>
      <input type="hidden" name="command" value="details"/>
			</form>
			</div>
</body>
</html>