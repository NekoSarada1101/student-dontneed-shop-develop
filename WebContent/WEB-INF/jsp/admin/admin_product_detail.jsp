<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<div>
    <img src="getImage" alt="商品画像">
    <h1>
        <%=productBeans.getProductName()%>
    </h1>
    <p>
        <%=productBeans.getPrice()%>
    </p>
    <p>
        <%=productBeans.getProductExplanation()%>
    </p>
    <p>
        <% if (productBeans.getIsSold()) { %>
        <span class="text-danger">販売済み</span>
        <% } else {%>
        <span class="text-success">在庫あり</span>
        <% } %>
    </p>
</div>

<form action="productUpdateInput" method="get">
    <button type="submit" class="btn btn-primary">変更する</button>
</form>

<form action="productDeleteCheck" method="get">
    <button type="submit" class="btn btn-danger">販売済みにする</button>
</form>

<form action="adminTop" method="get">
    <button type="submit" class="btn btn-outline-dark">戻る</button>
</form>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
