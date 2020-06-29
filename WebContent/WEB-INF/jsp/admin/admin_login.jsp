<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String msg = (String) request.getAttribute("msg");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<div class="text-center">
    <header class="container-fluid sticky-top">
        <nav class="navbar navbar-expand-lg bg-success px-1 px-sm-3">
            <a href="adminTop" class="text-white navbar-brand px-0 col-5 col-md-4 col-lg-2"><img src="img/logo.png" alt="logo" class="d-inline-block align-top px-0 col-12"></a>
            <%--<strong>S</strong>tudent <br> <strong>D</strong>ont't need <br> <strong>S</strong>hop</a>--%>

        </nav>
    </header>
    <h1 id="h1">管理者ログイン</h1>
    <form action="adminLogin" method="POST">
        <input type="text" name="adminMail" class="cp_txt" placeholder="UserID">
        <input type="password" name="adminPassword" class="cp_txt" placeholder="password">
        <input type="submit" value="ログイン"><br><br>
        <input type="reset" value="リセットする" class="cp_txt">


    </form>
    <br>
    <%if (msg != null) { %>
    <div class="alert alert-danger"><%= msg %>
    </div>
    <% } %>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
