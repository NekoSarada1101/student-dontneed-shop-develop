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
    <title>商品情報変更確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="mt-3 text-center">商品情報変更確認</h1>
<div class="row mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">

        <table class="table table-striped">
            <tbody>
            <tr class="row">
                <th class="col-3">商品名</th>
                <td class="col-9">
                    <%=productBeans.getProductName()%>
                </td>
            </tr>
                    <%=productName%>

            <tr class="row">
                <th class="col-3">価格</th>
                <td class="col-9">
                    <%=productBeans.getPrice()%>
                </td>
            </tr>
                    <%=price%>

            <tr class="row">
                <th class="col-3">画像</th>
                <td id="square-image" class="col-9">
                    <img src="getImage" alt="商品画像">
                </td>
            </tr>

            <tr class="row">
                <th class="col-3">商品説明</th>
                <td class="col-9">
                    <%=productBeans.getProductExplanation()%>
                </td>
            </tr>
                    <%=productExplanation%>

            <tr class="row">
                <th class="col-3">ジャンル</th>
                <td class="col-9">
                    <%
                        for (Map<String, Object> genreInfoMap : genreInfoList) {
                            if (productBeans.getGenreCode() == (int) genreInfoMap.get("genreCode")) {
                    %>
                    <%=genreInfoMap.get("genreName")%>
                    <%
                            }
                        }
                    %>
                </td>
            </tr>
            </tbody>
        </table>
                    <%=productService.escapeProcess((String) genreInfoMap.get("genreName"))%>

        <form action="productUpdateInput" method="get" class="col-6 ml-0">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <form action="productUpdateComplete" method="post" class="col-6 mr-0">
            <button type="submit" class="btn btn-primary btn-block">変更する</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

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

<%@ include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
