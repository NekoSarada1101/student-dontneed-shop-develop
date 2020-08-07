<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

    String errorMessage = (String) request.getAttribute("errorMessage");
    List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ログイン</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-3">
        <span href="memberTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<div>
    <h1 class="text-center my-5">会員ログイン</h1>

    <form action="memberLogin" method="POST" class="col-10 col-sm-8 col-md-6 col-lg-3 mx-auto">
        <div class="form-group">
            <label for="memberMail"><strong><i class="fas fa-envelope"></i> メールアドレス</strong></label>
            <input type="email" class="form-control" id="memberMail" name="memberMail" maxlength="100" required>
        </div>

        <div class="form-group">
            <label for="memberPassword"><strong><i class="fas fa-lock"></i> パスワード</strong></label>
            <input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="半角英数字"
                   maxlength="128"
                   pattern="[a-zA-Z0-9]+" title="半角英数字で入力してください。"
                   required>
        </div>

        <%if (errorMessage != null) { %>
        <div class="alert alert-danger mt-4">
            <%=errorMessage%>
        </div>
        <% } %>

        <button type="submit" class="btn btn-primary btn-block mt-5">ログイン</button>
    </form>

    <form action="memberInsertInput" method="get" class="col-10 col-sm-8 col-md-6 col-lg-3 mx-auto">
        <button type="submit" class="btn btn-info btn-block mt-3">会員登録</button>
    </form>

    <form action="adminLogin" method="get" class="col-10 col-sm-8 col-md-6 col-lg-3 mx-auto">
        <button type="submit" class="btn btn-link btn-block mt-5">管理者画面へ</button>
    </form>
</div>

<div class="col-12 col-md-10 mx-auto text-center">
    <img src="img/pict.png" alt="" class="img-fluid">
</div>

<div class="container-fluid">
    <div class="mt-5 px-5 row">
        <h1 class="col-12">新着</h1>
        <%
            int i = 0;
            for (ProductBeans productBeans : productList) {
        %>
        <div class="card col-6 col-md-4 col-lg-2 p-0" id="card">
            <div class="card-header square-image" id="square-image<%=i%>">
                <img src="getImageList?index=<%=i%>" alt="...">
            </div>

            <div class="card-body">
                <h5 class="card-title mb-1">
                    <%=ErrorCheckService.escapeProcess(productBeans.getProductName())%>
                </h5>

                <p class="card-subtitle text-muted mb-2">
                    <%
                        for (Map<String, Object> genreInfoMap : genreInfoList) {
                            if (productBeans.getGenreCode() == (int) genreInfoMap.get("genreCode")) {
                    %>
                    <%=ErrorCheckService.escapeProcess((String) genreInfoMap.get("genreName"))%>
                    <%
                            }
                        }
                    %>
                </p>

                <span class="card-text text-danger mb-3">
                    <%=ErrorCheckService.escapeProcess(String.valueOf(productBeans.getPrice()))%>円
                </span>
            </div>
        </div>
        <%
                if (i == 11) {
                    break;
                }
                i++;
            }
        %>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<script>
    document.addEventListener("DOMContentLoaded", imageResizeFunc);
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
