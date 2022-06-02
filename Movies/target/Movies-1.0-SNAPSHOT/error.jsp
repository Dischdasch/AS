<%--
  Created by IntelliJ IDEA.
  User: blehm
  Date: 30.05.2022
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="content">
    <%@ page isErrorPage="true" %>
    <h3>Sorry an exception occured!</h3>

Exception is: <%= exception %>
</div>
</body>
</html>
