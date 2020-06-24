<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
    List<Map<String, Object>> genreInfoList = (List<Map<String, Object>>) session.getAttribute("genreInfoList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者トップ</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<div class="row">
    <form action="productInsertInput" method="get" class="col-6">
        <button type="submit" class="btn btn-primary">商品登録</button>
    </form>

    <form action="salesCheck" method="post" class="col-6">
        <button type="submit" class="btn btn-primary">売上確認</button>
    </form>
</div>

<%
    int i  = 0;
    for (ProductBeans productBeans : productList) {
%>
<div class="card col-3">
    <img src="getImageList?index=<%=i%>" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title"><%=productBeans.getProductName()%>
        </h5>
        <p class="card-text">
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
        <p class="card-text text-danger"><%=productBeans.getPrice()%></p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
</div>
<%
        i++;
    }
%>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
