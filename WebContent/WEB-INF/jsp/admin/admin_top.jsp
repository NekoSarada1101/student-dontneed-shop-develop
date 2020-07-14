<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者トップ</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<div class="container-fluid">
    <div class="row mt-5">
        <form action="productInsertInput" method="get" class="col-6">
            <button type="submit" class="btn btn-primary btn-block btn-lg ml-auto col-12 col-md-8">商品登録</button>
        </form>

        <form action="salesCheck" method="post" class="col-6">
            <button type="submit" class="btn btn-info btn-block btn-lg mr-auto col-12 col-md-8">売上確認</button>
        </form>
    </div>

    <div class="mt-5 px-5 row">
        <%
            int i = 0;
            for (ProductBeans productBeans : productList) {
        %>
        <div class="card col-6 col-md-4 col-lg-2 p-0" id="card">
            <div class="card-header square-image" id="square-image<%=i%>">
                <img src="getImageList?index=<%=i%>" alt="...">
            </div>
            <div class="card-body">
                <h5 class="card-title mb-1">
                    <%=productBeans.getProductName()%>
                </h5>
                <p class="card-subtitle text-muted mb-2">
                    <%
                        for (Map<String, Object> genreInfoMap : genreInfoList) {
                            if (productBeans.getGenreCode() == (int) genreInfoMap.get("genreCode")) {
                    %>
                    <%=genreInfoMap.get("genreName")%>
                    <%
                            }
                        }
                    %>
                </p>
                <span class="card-text text-danger mb-3">
                    <%=productBeans.getPrice()%>円
                </span>
                <br>
                <% if (productBeans.getIsSold()) { %>
                <span class="text-danger">販売済み</span>
                <% } else {%>
                <span class="text-success">在庫あり</span>
                <% } %>
            </div>
            <div class="card-footer">
                <form action="adminProductDetail" method="post">
                    <input type="hidden" value="<%=i%>" name="index">
                    <button type="submit" class="btn btn-primary btn-block">詳細表示</button>
                </form>
            </div>
        </div>

        <%
                i++;
            }
        %>
    </div>
</div>


<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

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

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
