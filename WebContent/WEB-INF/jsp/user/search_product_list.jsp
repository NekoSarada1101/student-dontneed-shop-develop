<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

    int pageCount = (int) request.getAttribute("page");
    final int PRODUCT_ITEM_MAX = 30;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品検索結果</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div class="container-fluid">
    <%if (errorMessage != null) { %>
    <div class="alert alert-danger col-12">
        <%=errorMessage%>
    </div>
    <% } %>

    <div class="mt-5 px-5 row">
        <%
            for (int i = (pageCount - 1) * PRODUCT_ITEM_MAX; i < productList.size() && i <= (pageCount * PRODUCT_ITEM_MAX) - (1 * pageCount); i++) {
                ProductBeans productBeans = productList.get(i);
        %>
        <div class="card col-6 col-md-4 col-lg-2 p-0" id="card">
            <div class="card-header square-image" id="square-image<%=i%>">
                <img src="getImageList?index=<%=i%>" alt="...">
            </div>

            <div class="card-body">
                <h5 class="card-title mb-1">
                    <%=ErrorCheckService.escapeProcess(productBeans.getProductName())%>
                </h5>

                <p class="card-subtitle text-muted mb-2">
                    <%
                        for (Map<String, Object> genreInfoMap : genreInfoList) {
                            if (productBeans.getGenreCode() == (int) genreInfoMap.get("genreCode")) {
                    %>
                    <%=ErrorCheckService.escapeProcess((String) genreInfoMap.get("genreName"))%>
                    <%
                            }
                        }
                    %>
                </p>

                <span class="card-text text-danger mb-3">
                    <%=ErrorCheckService.escapeProcess(String.valueOf(productBeans.getPrice()))%>円
                </span>
            </div>

            <div class="card-footer">
                <form action="memberProductDetail" method="post" target="_blank">
                    <input type="hidden" value="<%=i%>" name="index">
                    <button type="submit" class="btn btn-primary btn-block">詳細表示</button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>

    <nav aria-label="Page navigation example" class="mt-4">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <%
                    if (pageCount == 1) {
                %>
                <form action="productListPagination" method="get" class="page-link">
                    <button type="submit" class="btn btn-link disabled">Previous</button>
                </form>
                <% } else { %>
                <form action="productListPagination" method="get" class="page-link">
                    <input type="hidden" value="<%=pageCount - 1%>" name="page">
                    <button type="submit" class="btn btn-link">Previous</button>
                </form>
                <% } %>
            </li>
            <%
                int pageMax = productList.size() / PRODUCT_ITEM_MAX;
                if (productList.size() % PRODUCT_ITEM_MAX > 0) {
                    pageMax++;
                }
                for (int i = 0; i < pageMax; i++) {
                    if (pageCount == i + 1) {
            %>
            <li class="page-item active text-white">
                    <%
                } else {
                %>
            <li class="page-item">
                <% } %>
                <form action="productListPagination" method="get" class="page-link">
                    <input type="hidden" value="<%=i + 1%>" name="page">
                    <button type="submit" class="btn btn-link"><%=i + 1%>
                    </button>
                </form>
            </li>
            <% } %>
            <li class="page-item">
                <%
                    if (pageCount == pageMax) {
                %>
                <form action="productListPagination" method="get" class="page-link">
                    <button type="submit" class="btn btn-link disabled">Next</button>
                </form>
                <% } else { %>
                <form action="productListPagination" method="get" class="page-link">
                    <input type="hidden" value="<%=pageCount + 1%>" name="page">
                    <button type="submit" class="btn btn-link">Next</button>
                </form>
                <% } %>
            </li>
        </ul>
    </nav>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<script>
    document.addEventListener("DOMContentLoaded", imageResizeFunc);
    window.addEventListener("resize", imageResizeFunc);

    function imageResizeFunc() {
        var width = document.getElementById('square-image<%=(pageCount - 1) * PRODUCT_ITEM_MAX%>').offsetWidth;
        console.log(width)
        var imageList = document.querySelectorAll(".square-image");
        console.log(imageList.length);
        width = String(width) + "px";
        for (var i = 0; i < imageList.length; i++) {
            var id = i + <%=(pageCount - 1) * PRODUCT_ITEM_MAX%>;
            document.getElementById("square-image" + id).style.height = width;
        }
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
