<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.print.attribute.standard.PrinterURI" %>
<%@ page import="shop.model.service.PurchaseService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ProductBeans> cartList = (List<ProductBeans>) session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>カート一覧</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div class="p-1 p-md-3 p-lg-5">

    <div class="row">
        <form action="memberTop" method="get" class="col-6 mx-auto mb-3 row">
            <button type="submit" class="btn btn-outline-dark btn-block col-8 ml-auto">トップ画面へ戻る</button>
        </form>
        <form action="purchaseCheck" method="get" class="col-6 mx-auto mb-3 row">
            <button type="submit" class="btn btn-primary btn-block col-8 mr-auto">購入</button>
        </form>
        <div class="col-7"><p></p></div>
        <table class="table table-hover col-11 col-md-8 mx-auto">
            <thead>
            <tr class="row">
                <th scope="col" class="col-2 col-lg-1">画像</th>
                <th scope="col" class="col-2 col-lg-3">商品名</th>
                <th scope="col" class="col-2">価格</th>
                <th scope="col" class="col-3"></th>
                <th scope="col" class="col-3"></th>
            </tr>
            </thead>

            <tbody>
            <%
                int i = 0;
                for (ProductBeans productBeans : cartList) {
            %>
            <tr class="row">
                <td scope="row" id="square-image<%=i%>" class="square-image col-2 col-lg-1">
                    <img src="getImageList?index=<%=i%>" alt="">
                </td>
                <td class="col-2 col-lg-3">
                    <strong>
                        <%=productBeans.getProductName()%>
                    </strong>
                </td>
                <td class="col-2">
                    <%=productBeans.getPrice() + "円"%>
                </td>
                <td class="col-3 text-center">
                    <form action="memberProductDetail" method="post">
                        <button type="submit" class="btn btn-primary">詳細表示</button>
                        <input type="hidden" value="<%=i%>" name="index">
                    </form>
                </td>
                <td class="col-3 text-center">
                    <form action="cartDelete" method="get">
                        <button type="submit" class="btn btn-danger">削除</button>
                    </form>
                </td>
            </tr>
            <%
                    i++;
                }
            %>
            </tbody>
        </table>
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
