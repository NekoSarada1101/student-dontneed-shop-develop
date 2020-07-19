<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退会完了画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h1 class="my-5 text-center">退会完了</h1>

<form action="memberLogin" method="get" class="mt-5">
    <button type="submit" class="btn btn-primary btn-block mx-auto col-6 col-lg-4">ログイン画面へ</button>
</form>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
