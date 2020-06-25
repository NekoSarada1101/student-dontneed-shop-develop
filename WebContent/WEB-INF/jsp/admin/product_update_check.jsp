<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
    List<Map<String, Object>> genreInfoList = (List<Map<String, Object>>) session.getAttribute("genreInfoList");
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

<div>
    <table class="table table-striped">
        <tbody>
        <tr>
            <th scope="row">商品名</th>
            <td><%=productBeans.getProductName()%></td>
        </tr>
        <tr>
            <th scope="row">価格</th>
            <td><%=productBeans.getPrice()%></td>
        </tr>
        <tr>
            <th scope="row">画像</th>
            <td><img src="getImage" alt="商品画像"></td>
        </tr>
        <tr>
            <th scope="row">商品説明</th>
            <td><%=productBeans.getProductExplanation()%></td>
        </tr>
        <tr>
            <th scope="row">ジャンル</th>
            <td>
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

    <form action="productUpdateComplete" method="post">
        <button type="submit" class="btn btn-primary">変更する</button>
    </form>

    <form action="productUpdateInput" method="get">
        <button type="submit" class="btn btn-outline-dark">戻る</button>
    </form>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
