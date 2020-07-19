<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-3">
        <span href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<div class="text-center mt-5 mx-auto col-12 col-md-6">
    <p>セッションタイムアウト、または不正な画面遷移が発生しました。再度ログインし直してください。</p>

    <form action="memberLogin" method="get" class="text-center mt-5">
        <button class="btn btn-outline-dark">ログイン画面へ戻る</button>
    </form>
</div>


<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
