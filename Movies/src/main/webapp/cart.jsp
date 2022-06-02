<%@ page import="com.example.classes.CartBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.classes.ShoppingCart" %><%--
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
        List<Integer> list = cart.getContents();
        for (int i=0;i<list.size();i++)
        {
            out.print(list.get(i));

        }%>
</div>
</body>
</html>
