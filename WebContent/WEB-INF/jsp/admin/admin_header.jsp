<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%
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
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>

        <form action="adminDetail" method="get" class="ml-auto">
            <button type="submit" class="btn btn-warning">
                <%=adminName + "さん"%>
                <br>
                <span>管理者詳細へ</span>
            </button>
        </form>

        <form action="adminLogout" method="get" class="ml-1 ml-sm-3">
            <button type="submit" class="btn btn-dark">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
