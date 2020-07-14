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

<div class="container-fluid">
	<div class="col-10 col-sm-8 col-md-6 mx-auto">
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

    <div class="mt-5">
        <form action="productUpdateInput" method="get">
    		<button type="submit" class="btn btn-primary btn-block btn-lg ml-auto col-lg-4 col-md-1">変更する</button>
		</form>

		<form action="productDeleteCheck" method="get">
    		<button type="submit" class="btn btn-danger btn-block btn-lg ml-auto col-lg-4 col-md-2">販売済みにする</button>
		</form>

		<form action="adminTop" method="get">
    		<button type="submit" class="btn btn-outline-dark btn-block btn-lg ml-auto col-lg-4 col-md-1">戻る</button>
		</form>
    </div>
</div>



<%@include file="/WEB-INF/jsp/script.jsp" %>

<script>
    window.onload = imageResizeFunc;
    window.addEventListener("resize", imageResizeFunc);

    function imageResizeFunc() {
        var width = document.getElementById('square-image0').offsetWidth;
        console.log(width)
        var imageList = document.querySelectorAll(".square-image");
        console.log(imageList.length);
        width = String(width) + "px";
        for (var i = 0; i < imageList.length; i++) {
            document.getElementById("square-image" + i).style.height = width;
        }
    }
</script>
</body>
</html>
