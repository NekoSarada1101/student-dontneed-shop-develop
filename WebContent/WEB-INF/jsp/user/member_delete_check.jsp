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

<h1 class="my-5 text-center">退会しますか？</h1>

<div class="col-11 col-sm-8 col-md-6 col-lg-4 row mx-auto">
    <form action="memberDetail" method="get" class="col-6 pl-0">
        <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
    </form>

    <form action="memberDeleteComplete" method="get" class="col-6 pl-0">
        <button type="submit" class="btn btn-danger btn-block">退会する</button>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
