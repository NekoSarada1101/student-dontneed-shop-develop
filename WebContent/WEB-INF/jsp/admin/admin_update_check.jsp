<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");

    String adminMail = ErrorCheckService.escapeProcess(adminBeans.getAdminMail());
    String name = ErrorCheckService.escapeProcess(adminBeans.getAdminName());
    String postalCode = ErrorCheckService.escapeProcess(adminBeans.getPostalCode());
    String address = ErrorCheckService.escapeProcess(adminBeans.getAddress());
%><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報変更確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="my-5 text-center">管理者情報変更確認</h1>

<div class="row">
    <div class="col-11 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <table class="table table-striped">
            <tbody>
            <tr class="row">
                <th class="col-4">メールアドレス</th>
                <td class="col-8">
                    <%=adminMail%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">パスワード</th>
                <td class="col-8">
                    ●●●●●●●●
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">名前</th>
                <td class="col-8">
                    <%=name%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">郵便番号</th>
                <td class="col-8">
                    <%=postalCode%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">住所</th>
                <td class="col-8">
                    <%=address%>
                </td>
            </tr>
        </table>

        <form action="adminUpdateInput" method="get" class="col-6 pl-0">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <form action="adminUpdateComplete" method="get" class="col-6 pr-0">
            <button type="submit" class="btn btn-primary btn-block">変更</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>

