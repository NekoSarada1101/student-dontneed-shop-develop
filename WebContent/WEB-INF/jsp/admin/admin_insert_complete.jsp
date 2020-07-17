<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報登録完了</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
     <link rel="stylesheet" href="css/admin_header.css">
</head>
<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>
    </nav>
</header>

<h1 class="mt-3 text-center">管理者情報登録完了</h1>

<form action="adminLogin" method="get" class="mt-5">
    <button type="submit" class="btn btn-primary btn-block mx-auto col-6 col-lg-4">ログイン画面へ</button>
</form>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>
<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
