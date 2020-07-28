<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%
    List<ProductBeans> purchaseList = (List<ProductBeans>) session.getAttribute("productList");

    int total = 0;
    for (ProductBeans productBeans : purchaseList) {
        total += productBeans.getPrice();
    }
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

<h1 class="text-center my-5">購入確認</h1>

<div class="px-1 px-md-3 px-lg-5">
    <div class="row">
        <form action="cartDisplay" method="get" class="col-6 mx-auto mb-3 row">
            <button type="submit" class="btn btn-outline-dark btn-block col-8 ml-auto">戻る</button>
        </form>

        <form action="purchaseComplete" method="post" class="col-6 mx-auto mb-3 row" onsubmit="this.disabled = true;">
            <input type="submit" class="btn btn-primary btn-block col-8 mr-auto" value="購入">
        </form>
    </div>

    <div class="col-7">
        <p class="text-danger">
            <%=request.getAttribute("errorMessage")%>
        </p>
    </div>

    <div class="col-11 col-md-8 mx-auto">
        <h3 class="text-left">
            合計金額：<%=total + "円"%>
        </h3>
    </div>

    <table class="table table-hover col-11 col-md-8 mx-auto">
        <thead>
        <tr class="row">
            <th scope="col" class="col-3 col-lg-2">画像</th>
            <th scope="col" class="col-3 col-lg-4">商品名</th>
            <th scope="col" class="col-6">価格</th>
        </tr>
        </thead>

        <tbody>
        <%
            int i = 0;
            for (ProductBeans productBeans : purchaseList) {
        %>
        <tr class="row">
            <td scope="row" id="square-image<%=i%>" class="square-image col-3 col-lg-2">
                <img src="getImageList?index=<%=i%>" alt="">
            </td>
            <td class="col-3 col-lg-4">
                <strong>
                    <%=ErrorCheckService.escapeProcess(productBeans.getProductName())%>
                </strong>
            </td>
            <td class="col-6">
                <%=ErrorCheckService.escapeProcess(String.valueOf(productBeans.getPrice())) + "円"%>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>

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
