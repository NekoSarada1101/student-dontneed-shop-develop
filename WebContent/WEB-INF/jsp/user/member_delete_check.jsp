<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退会確認画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<div>
    <h1>退会しますか？</h1>
    <form action="memberDeleteComplete" method="post">
        <button type="submit" class="btn btn-danger">退会する</button>
    </form>

    <form action="memberDetail" method="get">
        <button type="submit" class="btn btn-outline-dark">戻る</button>
    </form>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
