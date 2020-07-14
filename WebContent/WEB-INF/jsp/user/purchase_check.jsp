<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ProductBeans> purchaseList = (List<ProductBeans>) session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div>
    <div class="row mt-5">
        <form action="cartDisplay" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block btn-lg ml-auto col-12 col-md-8">戻る</button>
        </form>

        <form action="purchaseComplete" method="post" class="col-6">
            <button type="submit" class="btn btn-primary btn-block btn-lg mr-auto col-12 col-md-8">購入</button>
        </form>
    </div>

    <p class="text-danger">
        <%=request.getAttribute("errorMessage")%>
    </p>

    <%
        int i = 0;
        for (ProductBeans productBeans : purchaseList) {
    %>
    <div class="card mb-3">
        <div class="row no-gutters">
            <div class="col-md-4 square-image" id="square-image">
                <img src="getImageList?index=<%=i%>" alt="...">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title"><%=productBeans.getProductName()%>
                    </h5>
                    <p class="card-text text-danger"><%=productBeans.getPrice() + "円"%>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <%
            i++;
        }
    %>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<script>
    window.onload = imageResizeFunc;
    window.addEventListener("resize", imageResizeFunc);

    function imageResizeFunc() {
        var width = document.getElementById("square-image0").offsetWidth;
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
