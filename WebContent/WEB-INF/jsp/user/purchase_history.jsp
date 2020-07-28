<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page import="shop.model.service.PurchaseService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    PurchaseService purchaseService = new PurchaseService();
    List<Map<String, Object>> purchaseList = purchaseService.fetchPurchaseHistory(((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入履歴</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h1 class="text-center my-5">購入履歴</h1>

<div class="px-1 px-md-3 px-lg-5">
    <div class="row">
        <form action="memberTop" method="get" class="col-11 col-md-8 mx-auto mb-3 row">
            <button type="submit" class="btn btn-outline-dark btn-block col-5 mr-auto">トップ画面へ戻る</button>
        </form>

        <div class="col-7"></div>

        <table class="table table-hover col-11 col-md-8 mx-auto">
            <thead>
            <tr class="row">
                <th scope="col" class="col-3 col-lg-2">画像</th>
                <th scope="col" class="col-3">商品名</th>
                <th scope="col" class="col-3">価格</th>
                <th scope="col" class="col-3 col-lg-4">購入日</th>
            </tr>
            </thead>

            <tbody>
            <%
                int i = 0;
                for (Map<String, Object> purchaseMap : purchaseList) {
                    ProductBeans productBeans = (ProductBeans) purchaseMap.get("productBeans");
            %>
            <tr class="row">
                <td scope="row" id="square-image<%=i%>" class="square-image col-3 col-lg-2">
                    <img src="getImageList?index=<%=i%>" alt="">
                </td>
                <td class="col-3">
                    <strong>
                        <%=ErrorCheckService.escapeProcess(productBeans.getProductName())%>
                    </strong>
                </td>
                <td class="col-3">
                    <%=ErrorCheckService.escapeProcess(String.valueOf(productBeans.getPrice())) + "円"%>
                </td>
                <td class="col-3 col-lg-4">
                    <%=ErrorCheckService.escapeProcess((String) purchaseMap.get("purchaseDate"))%>
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
