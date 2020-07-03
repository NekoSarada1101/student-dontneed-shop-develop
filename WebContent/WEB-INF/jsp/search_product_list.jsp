<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="shop.model.*" %>

<%
List<ProductBeans>productList = (List<ProductBeans>)session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索結果</title>
 <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
 <%@include file="/css/common.css" %>
</head>


<body>
<div class="row row-cols-1 row-cols-md-3">
<%
for(ProductBeans bean:productList){
%>
	<div class="col mb-4">
		<div class="card" style="width: 18rem;">
		  <img src="..." class="card-img-top" alt="カードの画像">
		  <div class="card-body">
		    <h5 class="card-title"><%=bean.getproductName %></h5>
		    <h5 class="card-title"><%=bean.get %>ジャンル</h5>
		    <h5 class="card-title"><%=bean.getprice %></h5>
		    <p class="card-text"><%=bean.getproductExeplanation%></p>
		    <a href="#" class="btn btn-primary">商品詳細表示</a>
		  </div>
		</div>
	</div>
<%
}
%>
</div>
</body>
</html>