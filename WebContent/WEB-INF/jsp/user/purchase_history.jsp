<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
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
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div class="p-1  p-md-3 p-lg-5">

    <div class="row">
        <form action="adminTop" method="get" class="col-11 col-md-8 mx-auto mb-3 row">
            <button type="submit" class="btn btn-outline-dark btn-block col-5 mr-auto">トップ画面へ戻る</button>
        </form>
        <div class="col-7"></div>
        <table class="table table-hover col-11 col-md-8 mx-auto">
            <thead>
            <tr>
                <th scope="col">画像</th>
                <th scope="col">商品名</th>
                <th scope="col">価格</th>
                <th scope="col">購入日</th>
                <th scope="col"></th>
            </tr>
            </thead>

            <tbody>
            <%
                int i = 0;
                for (Map<String, Object> purchaseMap : purchaseList) {
                    ProductBeans productBeans = (ProductBeans) purchaseMap.get("productBeans");
            %>
            <tr>
                <td scope="row" id="table-image<%=i%>" class="table-image">
                    <img src="getImageList?index=<%=i%>" alt="">
                </td>
                <td>
                    <strong>
                        <%=productBeans.getProductName()%>
                    </strong>
                </td>
                <td>
                    <%=productBeans.getPrice() + "円"%>
                </td>
                <td>
                    <%=purchaseMap.get("purchaseDate")%>
                </td>
                <td>
                    <form action="productDetail" method="post">
                        <button type="submit" class="btn btn-primary">詳細表示</button>
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
        var width = document.getElementById('table-image0').offsetWidth;
        console.log(width)
        var tableImage = document.querySelectorAll(".table-image");
        console.log(tableImage.length);
        width = String(width) + "px";
        for (var i = 0; i < tableImage.length; i++) {
            document.getElementById("table-image" + i).style.height = width;
        }
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
