<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");

    ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品情報変更入力</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="my-5 text-center">商品情報変更入力</h1>

<div class="row">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <%if (errorMessage != null) { %>
        <div class="alert alert-danger col-12">
            <%=errorMessage%>
        </div>
        <% } %>

        <form action="productUpdateCheck" method="post" class="col-12 mx-auto" id="form" enctype=multipart/form-data>
            <div class="form-group row">
                <label for="productName" class="col-12"><strong>商品名</strong></label>
                <input type="text" value="<%=productBeans.getProductName()%>" class="form-control col-12"
                       id="productName"
                       name="productName" maxlength="30" required>
            </div>

            <div class="form-group row">
                <label for="price" class="col-12"><strong>価格</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control col-12" id="price"
                       name="price"
                       max="2147483647" min="0" required>
            </div>

            <div class="form-group row">
                <label for="image" class="col-12"><strong>画像</strong></label>
                <input type="file" class="form-control col-12" id="image" name="image" onchange="previewImage(this);"
                       accept="image/*" required>
                <div>
                    Preview:<br>
                    <img class="position-static" id="preview" src="getImage" style="max-width:400px;">
                </div>
            </div>

            <div class="form-group row">
                <label for="productExplanation" class="col-12"><strong>商品説明</strong></label>
                <textarea class="form-control col-12" id="productExplanation"
                          name="productExplanation" rows="10"
                          cols="40" maxlength="400" required><%=productBeans.getProductExplanation()%></textarea>
            </div>

            <div class="form-group row">
                <label for="genre" class="col-12"><strong>ジャンル</strong></label>
                <select class="custom-select form-control col-6" id="genre" name="genre">
                    <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                    <option value="<%=genreInfoMap.get("genreCode")%>">
                        <%=genreInfoMap.get("genreName")%>
                    </option>
                    <% } %>
                </select>
            </div>
        </form>

        <form action="adminProductDetail" method="post" class="col-6">
            <input type="hidden" value="productUpdateInput" name="from">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="px-3 col-6">
            <button type="submit" class="btn btn-primary btn-block" form="form">変更</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<script>
    function previewImage(obj) {
        var fileReader = new FileReader();
        fileReader.onload = (function () {
            document.getElementById('preview').src = fileReader.result;
        });
        fileReader.readAsDataURL(obj.files[0]);
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
