<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%
    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

    String adminName = ((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminName();
    adminName = ErrorCheckService.escapeProcess(adminName);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ヘッダー</title>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white" style="width: 160px">KIK</a>

        <p class="text-left text-white my-auto" style="width: 180px">
            ようこそ！
            <br>
            <%=adminName%>さん
        </p>

        <div class="dropdown my-auto ml-auto" style="width: 120px">
            <button class="nav-link btn btn-warning dropdown-toggle col-12" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                メニュー
            </button>

            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <form action="adminDetail" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-user"></i> 管理者詳細</span>
                    </button>
                </form>

                <form action=memberLogin method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-in-alt"></i> 会員ログイン</span>
                    </button>
                </form>

                <form action="adminLogout" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-out-alt"></i> ログアウト</span>
                    </button>
                </form>
            </div>
        </div>

        <form action="adminProductSearchAndDisplay" method="post"
              class="form-inline form-group my-md-auto mb-1 ml-lg-3 w-100">
            <select class="custom-select form-control bg-white col-2 col-md-1" id="genre" name="genreCode">
                <option value="0">すべて</option>
                <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                <option value="<%=genreInfoMap.get("genreCode")%>">
                    <%=genreInfoMap.get("genreName")%>
                </option>
                <% } %>
            </select>

            <select class="custom-select form-control bg-white rounded-0 col-2 col-md-1" name="sortColumn">
                <option value="product_id">登録日</option>
                <option value="product_name">商品名</option>
                <option value="price">価格</option>
            </select>

            <select class="custom-select form-control bg-white rounded-0 col-2 col-md-1" name="sortOrder">
                <option value="asc">昇順</option>
                <option value="desc" selected>降順</option>
            </select>

            <input type="text" class="form-control bg-white rounded-0 col-4 col-md-8" name="searchWord">

            <input type="hidden" value="1" name="page">
            <button type="submit" class="btn btn-warning col-2 col-md-1" id="search"><i class="fas fa-search"></i>
            </button>
        </form>
    </nav>
</header>
</body>
</html>
