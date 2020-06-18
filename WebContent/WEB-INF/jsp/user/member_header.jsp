<%@ page import="org.omg.CORBA.OBJ_ADAPTER" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Map<String, Object>> genreInfoList = (List<Map<String, Object>>) session.getAttribute("genreInfoList");
    String memberName = ((MemberBeans) session.getAttribute("member")).getMemberName();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ヘッダー</title>
</head>

<body class="container-fluid">
<header>
    <nav class="row">
        <a href="memberTop"><strong>Student <br> Don't need <br> Shop</strong></a>

        <form action="/productSearchAndDisplay" method="post" class="form-inline form-group">
            <select class="custom-select form-control">
                <option value="0">すべて</option>
                <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                <option value="<%=genreInfoMap.get("genreCode")%>">
                    <%=genreInfoMap.get("genreName")%>
                </option>
                <% } %>
            </select>

            <select class="custom-select form-control">
                <option value="product_id">登録日</option>
                <option value="product_name">商品名</option>
                <option value="price">価格</option>
            </select>

            <select class="custom-select form-control">
                <option value="asc">昇順</option>
                <option value="desc">降順</option>
            </select>

            <input type="text" class="form-control">

            <button type="submit"><i class="fas fa-search"></i></button>
        </form>

        <form action="/memberDetail" method="post">
            <button type="submit">
                <%=memberName + "さん"%>
                <br>
                <span>会員詳細へ</span>
            </button>
        </form>

        <form action="/purchaseHistory" method="post">
            <button type="submit">
                <span>購入履歴</span>
            </button>
        </form>

        <form action="/cartDisplay" method="post">
            <button type="submit">
                <i class="fas fa-shopping-cart"></i>
            </button>
        </form>

        <form action="logout" method="get">
            <button type="submit">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
