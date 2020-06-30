<%@ page import="org.omg.CORBA.OBJ_ADAPTER" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.service.ProductService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String memberName = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberName();

    ProductService productService = new ProductService();
    List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ヘッダー</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/member_header.css">
</head>

<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <strong class="mr-3" style="width: 160px">
            <a href="memberTop" class="navbar-brand text-white">Student<br>Don`t need Shop</a>
        </strong>

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
                <form action="cartDisplay" method="post" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-shopping-cart"></i> カート閲覧</span>
                    </button>
                </form>
                <form action=adminLogin method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-in-alt"></i> 管理者ログイン</span>
                    </button>
                </form>
                <form action="logout" method="get" class="dropdown-item">
                    <button type="submit" class="btn btn-link text-dark">
                        <span><i class="fas fa-sign-out-alt"></i> ログアウト</span>
                    </button>
                </form>
            </div>
        </div>

        <form action="productSearchAndDisplay" method="post" class="form-inline form-group my-md-auto mb-1 ml-lg-3 w-100">
            <select class="custom-select form-control col-2 col-md-1" id="genre">
                <option value="0">すべて</option>
                <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                <option value="<%=genreInfoMap.get("genreCode")%>">
                    <%=genreInfoMap.get("genreName")%>
                </option>
                <% } %>
            </select>

            <select class="custom-select form-control rounded-0 col-2 col-md-1">
                <option value="product_id">登録日</option>
                <option value="product_name">商品名</option>
                <option value="price">価格</option>
            </select>

            <select class="custom-select form-control rounded-0 col-2 col-md-1">
                <option value="asc">昇順</option>
                <option value="desc" selected>降順</option>
            </select>

            <input type="text" class="form-control rounded-0 col-4 col-md-8">

            <button type="submit" class="btn btn-warning col-2 col-md-1" id="search"><i class="fas fa-search"></i>
            </button>
        </form>
    </nav>
</header>
</body>
</html>
