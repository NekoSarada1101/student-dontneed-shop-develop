<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者削除確認画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="my-5 text-center">管理者情報削除確認</h1>

<div class="col-11 col-sm-8 col-md-6 col-lg-4 row mx-auto">
    <form action="adminDetail" method="get" class="col-6 pl-0">
        <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
    </form>

    <form action="adminDeleteComplete" method="get" class="col-6 pl-0">
        <button type="submit" class="btn btn-danger btn-block">削除</button>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
