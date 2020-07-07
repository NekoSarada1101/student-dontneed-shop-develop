<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
<header class="container-fluid sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <span href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<div class="text-center">
    <h1 id="h1">管理者ログイン</h1>
    <form action="adminLogin" method="POST">
        <input type="text" name="adminMail" class="cp_txt" placeholder="UserID">
        <input type="password" name="adminPassword" class="cp_txt" placeholder="password">
        <input type="submit" value="ログイン"><br><br>
        <input type="reset" value="リセットする" class="cp_txt">

    </form>
    <br>
    <%if (errorMessage != null) { %>
    <div class="alert alert-danger"><%= errorMessage %>
    </div>
    <% } %>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
