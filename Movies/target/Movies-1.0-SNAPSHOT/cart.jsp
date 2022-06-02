<%@ page import="com.example.classes.CartBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.classes.ShoppingCart" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: blehm
  Date: 01.06.2022
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
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
