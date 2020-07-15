<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="shop.model.bean.ProductBeans" %>
<%@page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品検索結果</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div class="container-fluid">
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

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

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
