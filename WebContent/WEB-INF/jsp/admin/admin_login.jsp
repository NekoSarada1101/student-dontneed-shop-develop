<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String msg = (String)request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン画面</title>
<%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
	<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>
	<div class="text-center">
		<h1 id="h1">管理者ログイン</h1>
		<form action="auth" method="POST">
			<input type="text" name="adminMail" class="cp_txt" placeholder="UserID">
			<input type="password" name="adminPassword" class="cp_txt"placeholder="password">
			<input type="submit" value="ログイン"><br><br>
			<input type="reset" value="リセットする" class="cp_txt">


		</form><br>
		<%if(msg != null){ %> <div class="alert alert-danger"><%= msg %></div> <% } %>
	</div>


</body>
</html>