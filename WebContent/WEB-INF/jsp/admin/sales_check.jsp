<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="shop.model.service.PurchaseService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    PurchaseService purchaseService = new PurchaseService();
    ProductService productService = new ProductService();

    List<Map<String, Object>> salesList = purchaseService.fetchSalesInfo(((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail());
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>売上確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>
<h1 class="mt-3 text-center">売上確認</h1>
<div class="p-1  p-md-3 p-lg-5">
    <div class="row">
        <form action="adminTop" method="get" class="col-11 col-md-8 mx-auto mb-3 row">
            <button type="submit" class="btn btn-outline-dark btn-block col-4 mr-auto">戻る</button>
        </form>

        <table class="table table-hover col-11 col-md-8 mx-auto">
            <thead>
            <tr class="row">
                <th scope="col" class="col-2">商品ID</th>
                <th scope="col" class="col-3">商品名</th>
                <th scope="col" class="col-2">ジャンル</th>
                <th scope="col" class="col-2">価格</th>
                <th scope="col" class="col-3">購入日</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (Map<String, Object> salesMap : salesList) {
                    ProductBeans productBeans = (ProductBeans) salesMap.get("productBeans");
            %>
            <tr class="row">
                <td scope="row" class="col-2">
                    <%=productBeans.getProductId()%>
                </td>
                <td class="col-3">
                    <%=productBeans.getProductName()%>
                </td>
                <td class="col-2">
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
                <td class="col-2">
                    <%=productBeans.getPrice() + "円"%>
                </td>
                <td class="col-3">
                    <%=salesMap.get("purchaseDate")%>
                </td>
            </tr>
            <%
                } %>
            </tbody>
        </table>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
