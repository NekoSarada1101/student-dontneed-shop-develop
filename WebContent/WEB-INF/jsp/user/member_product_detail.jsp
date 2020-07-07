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
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

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
</div>

<form action="cartInsert" method="get">
    <button type="submit" class="btn btn-primary">カートに追加する</button>
</form>

<form action="memberTop" method="get">
    <button type="submit" class="btn btn-outline-dark">戻る</button>
</form>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
