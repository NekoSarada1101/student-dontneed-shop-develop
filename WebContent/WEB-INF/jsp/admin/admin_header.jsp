<%@ page import="org.omg.CORBA.OBJ_ADAPTER" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String adminName = ((AdminBeans) session.getAttribute("admin")).getAdminName();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ヘッダー</title>
</head>

<body class="container-fluid">
<header>
    <nav class="row">
        <a href="adminTop"><strong>Student <br> Don't need <br> Shop</strong></a>

        <form action="/adminDetail" method="post">
            <button type="submit">
                <%=adminName + "さん"%>
                <br>
                <span>管理者詳細へ</span>
            </button>
        </form>

        <form action="logout" method="get">
            <button type="submit">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
