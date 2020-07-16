<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");

    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

    String productName = productService.escapeProcess(productBeans.getProductName());
    String price = productService.escapeProcess(String.valueOf(productBeans.getPrice()));
    String productExplanation = productService.escapeProcess(productBeans.getProductExplanation());
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
    <div class="mt-5 px-5 row">
        <div class="card col-12 col-sm-8 col-md-6 col-lg-4 p-0 mx-auto" id="card">
            <div class="card-header square-image" id="square-image">
                <img src="getImage" alt="...">
            </div>
            <div class="card-body">
                <h5 class="card-title mb-1">
                    <%=productName%>
                </h5>
                <p class="card-subtitle text-muted mb-2">
                    <%
                        for (Map<String, Object> genreInfoMap : genreInfoList) {
                            if (productBeans.getGenreCode() == (int) genreInfoMap.get("genreCode")) {
                    %>
                    <%=productService.escapeProcess((String) genreInfoMap.get("genreName"))%>
                    <%
                            }
                        }
                    %>
                </p>
                <p class="card-text text-danger mb-3">
                    <%=price%>円
                </p>
                <p class="card-text mb-3">
                    <%=productExplanation%>
                </p>
                <p class="card-text mb-3">
                    <% if (productBeans.getIsSold()) { %>
                    <span class="text-danger">販売済み</span>
                    <% } else {%>
                    <span class="text-success">在庫あり</span>
                    <% } %>
                </p>
            </div>
            <div class="card-footer">
                <form action="productUpdateInput" method="get">
                    <button type="submit" class="btn btn-primary btn-block mb-2">変更する
                    </button>
                </form>

                <form action="productDeleteCheck" method="get">
                    <button type="submit" class="btn btn-danger btn-block mb-2">販売済みにする
                    </button>
                </form>

                <form action="adminTop" method="get">
                    <button type="submit" class="btn btn-outline-dark btn-block">戻る
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

<script>
    window.onload = imageResizeFunc;
    window.addEventListener("resize", imageResizeFunc);

    function imageResizeFunc() {
        var width = document.getElementById('square-image').offsetWidth;
        console.log(width)
        width = String(width) + "px";
        document.getElementById("square-image").style.height = width;
    }
</script>
</body>
</html>
