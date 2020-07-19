<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.Map" %>
<%@ page import="shop.model.service.PurchaseService" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");

    String productName = ErrorCheckService.escapeProcess(productBeans.getProductName());
    String price = ErrorCheckService.escapeProcess(String.valueOf(productBeans.getPrice()));
    String productExplanation = ErrorCheckService.escapeProcess(productBeans.getProductExplanation());

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

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
                    <%=ErrorCheckService.escapeProcess((String) genreInfoMap.get("genreName"))%>
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
            </div>

            <div class="card-footer row m-0">
                <form action="memberTop" method="get" class="col-6">
                    <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
                </form>

                <form action="cartInsert" method="post" class="col-6">
                    <%
                        PurchaseService purchaseService = new PurchaseService();
                        if (purchaseService.checkExistsCart(productBeans.getProductId())) {
                    %>
                    <button type="submit" class="btn btn-primary btn-block" disabled>カートに追加済み</button>
                    <%
                    } else {
                    %>
                    <button type="submit" class="btn btn-primary btn-block">カートに追加</button>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

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

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
