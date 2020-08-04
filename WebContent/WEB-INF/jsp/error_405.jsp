<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>405</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-3">
        <span href="memberTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<h1 class="text-center mt-5">405</h1>
<p class="text-center">HTTPのGETメソッドは、このURLではサポートされていません。。</p>

<div class="text-center row">
    <form action="memberLogin" method="get" class="col-6 text-right">
        <button class="btn btn-outline-primary">会員画面へ</button>
    </form>

    <form action="adminLogin" method="get" class="col-6 text-left">
        <button class="btn btn-outline-success">管理者画面へ</button>
    </form>
</div>

<div class="col-12 col-md-10 mx-auto text-center mt-5">
    <img src="img/question.png" alt="" class="img-fluid">
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
