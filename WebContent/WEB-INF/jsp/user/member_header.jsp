<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

    String memberName = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberName();
    memberName = ErrorCheckService.escapeProcess(memberName);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ヘッダー</title>
    <link rel="stylesheet" href="css/member_header.css">
</head>

<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="memberTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>

        <p class="text-left text-white my-auto" style="width: 180px">
            ようこそ！
            <br>
            <%=memberName%>さん
        </p>

        <div class="dropdown my-auto ml-auto" style="width: 120px">
            <button class="nav-link btn btn-warning dropdown-toggle col-12" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                メニュー
            </button>

            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <form action="memberDetail" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-user"></i> 会員詳細</span>
                    </button>
                </form>

                <form action="purchaseHistory" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-history"></i> 購入履歴</span>
                    </button>
                </form>

                <form action="cartDisplay" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-shopping-cart"></i> カート閲覧</span>
                    </button>
                </form>

                <form action=adminLogin method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-in-alt"></i> 管理者ログイン</span>
                    </button>
                </form>

                <form action="memberLogout" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-out-alt"></i> ログアウト</span>
                    </button>
                </form>
            </div>
        </div>

        <form action="productSearchAndDisplay" method="post"
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
