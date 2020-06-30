<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員大会完了画面</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

	<div>
		<h1>退会完了しました</h1>

		<form action="memberTop" method="post">
        	<button type="submit" class="btn btn-primary">トップへ戻る</button>
    	</form>
	</div>

	<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>