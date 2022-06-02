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
</div>
</body>
</html>
