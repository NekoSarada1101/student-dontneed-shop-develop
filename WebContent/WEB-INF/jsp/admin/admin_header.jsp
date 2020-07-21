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
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white" style="width: 100px">KIK</a>

        <p class="text-left text-white my-auto" style="width: 100px">
            ようこそ！
            <br>
            <%=adminName%>さん
        </p>

        <div class="dropdown my-auto ml-3" style="width: 120px">
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
    </nav>
</header>
</body>
</html>
