<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="java.util.Map" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");

    String adminMail = ErrorCheckService.escapeProcess(adminBeans.getAdminMail());
    String name = ErrorCheckService.escapeProcess(adminBeans.getAdminName());
    String postalCode = ErrorCheckService.escapeProcess(adminBeans.getPostalCode());
    String address = ErrorCheckService.escapeProcess(adminBeans.getAddress());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="text-center my-5">管理者情報</h1>

<div class="row">
    <div class="row col-8 col-md-4 mx-auto">
        <ul class="list-group list-group-flush col-12">
            <li class="list-group-item">
                <label for="adminMail"><strong>管理者メール</strong></label>
                <p id="adminMail">
                    <%=adminMail%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="adminPassword"><strong>パスワード</strong></label>
                <p id="adminPassword">●●●●●●●●</p>
            </li>
            <li class="list-group-item">
                <label for="adminName"><strong>管理者名</strong></label>
                <p id="adminName">
                    <%=name%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="postalCode"><strong>郵便番号</strong></label>
                <p id="postalCode">
                    <%=postalCode%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="address"><strong>住所</strong></label>
                <p id="address">
                    <%=address%>
                </p>
            </li>
        </ul>

        <form action="adminDeleteCheck" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-danger btn-block">退会</button>
        </form>

        <form action="adminUpdateInput" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-info btn-block">変更</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
