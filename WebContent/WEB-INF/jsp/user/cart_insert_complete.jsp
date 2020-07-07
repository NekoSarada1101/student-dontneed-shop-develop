<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%
    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>カート追加完了</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h2>カートに追加しました</h2>

<div class="card mb-3" style="max-width: 500px;">
    <div class="row no-gutters">
        <div class="col-lg-6">
            <img src="getImage" class="card-img" alt="...">
        </div>
        <div class="col-lg-6">
            <div class="card-body">
                <div class="card-title">商品名
                    <%=productBeans.getProductName()%>
                </div>
                <div class="card-title">値段
                    <%=productBeans.getPrice()%>
                </div>
            </div>
        </div>
    </div>
</div>

<form action="cartDisplay" method="get">
    <input type="submit" value="カートに進む">
</form>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
