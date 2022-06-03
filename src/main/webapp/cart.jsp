<%@ page import="com.example.moviegf6.ShoppingCart" %>
<%@ page import="java.util.Set" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Shopping Cart</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="content">
    <h2>Shopping Cart</h2>
    <% ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Set<Integer> contents = cart.getContents();
        for (int movie: contents)
        {
            out.print(String.valueOf(movie));
        }%>
</div>
</body>
</html>
