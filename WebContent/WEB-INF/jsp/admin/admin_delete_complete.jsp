<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者削除完了画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="my-5 text-center">管理者情報削除完了</h1>

<form action="adminLogout" method="get" class="mt-5">
    <button type="submit" class="btn btn-primary btn-block mx-auto col-6 col-lg-4">ログイン画面へ</button>
</form>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
