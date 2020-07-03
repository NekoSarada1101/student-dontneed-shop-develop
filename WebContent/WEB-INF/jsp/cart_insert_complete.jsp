<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%boolean couldInsert =(boolean)request.getAttribute("couldInsert");%>
<% %>
<% %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート追加完了画面</title>
<%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>


<body>
<% ProductBeans productBeans = (ProductBeans)session.getAttribute("productBeans"); %>
<% MemberBeans memberBeans = (MemberBeans)session.getAttribute("memberBeans");%>
<% String productName = productBeans.getProductName(); %>
<% int price = productBeans.getPrice() ; %>

<h2>カートに追加しました</h2>

<div class="card mb-3" style="max-width: 500px;">
      <div class="row no-gutters">
        <div class="col-lg-6">
          <img src="images/notepc-wp.jpeg" class="card-img" alt="...">
        </div>
        <div class="col-lg-6">
          <div class="card-body">
            <div class="card-title">商品名<%=productName %></div>
            <div class="card-title">値段<%=price %></div>
          </div>
        </div>
      </div>
</div>

<form action="" method="POST">
	<input type="submit" value="カートに進む">
</form>


</body>
</html>