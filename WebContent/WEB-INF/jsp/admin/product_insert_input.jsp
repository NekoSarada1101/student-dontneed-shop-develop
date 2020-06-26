<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
    if (productBeans == null) {
        productBeans = new ProductBeans();
        productBeans.setProductName("");
        productBeans.setPrice(0);
        productBeans.setProductExplanation("");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品情報登録入力</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<div>
    <form action="productInsertCheck" method="post" enctype=multipart/form-data>
        <div class="form-group">
            <label for="productName">商品名</label>
            <input type="text" value="<%=productBeans.getProductName()%>" class="form-control" id="productName"
                   name="productName">
        </div>
        <div class="form-group">
            <label for="price">価格</label>
            <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price">
        </div>
        <div class="form-group">
            <label for="image">画像</label>
            <input type="file" class="form-control" id="image" name="image" onchange="previewImage(this);" accept="image/*">
            <p>Preview:<br>
                <img id="preview" src="getImage" style="max-width:200px;">
            </p>
        </div>
        <div class="form-group">
            <label for="productExplanation">商品説明</label>
            <input type="text" value="<%=productBeans.getProductExplanation()%>" class="form-control" id="productExplanation" name="productExplanation">
        </div>
        <div class="form-group">
            <labal for="genre">ジャンル</labal>
            <select class="custom-select form-control" id="genre" name="genre">
                <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                <option value="<%=genreInfoMap.get("genreCode")%>">
                    <%=genreInfoMap.get("genreName")%>
                </option>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">登録する</button>
    </form>

    <form action="adminTop" method="get">
        <button type="submit" class="btn btn-outline-dark">戻る</button>
    </form>
</div>

<script>
    function previewImage(obj) {
        var fileReader = new FileReader();
        fileReader.onload = (function() {
            document.getElementById('preview').src = fileReader.result;
        });
        fileReader.readAsDataURL(obj.files[0]);
    }
</script>
<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
