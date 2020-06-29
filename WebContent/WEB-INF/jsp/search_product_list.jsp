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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
		    <h5 class="card-title"><%=bean.get %></h5>
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