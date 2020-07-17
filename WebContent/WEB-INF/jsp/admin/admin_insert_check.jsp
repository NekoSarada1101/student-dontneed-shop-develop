<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="shop.model.service.CommonService" %>
<%
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");

    CommonService commonService = new CommonService();
    String adminMail = commonService.escapeProcess(adminBeans.getAdminMail());
    String adminName = commonService.escapeProcess(adminBeans.getAdminName());
    String postalCode = commonService.escapeProcess(adminBeans.getPostalCode());
    String address = commonService.escapeProcess(adminBeans.getAddress());

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報登録確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/admin_header.css">
</head>

<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>
    </nav>
</header>
<h1 class="mt-3 text-center">管理者情報登録確認</h1>
<div class="row  mt-3">
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
                    <%=adminName%>
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

        <form action="adminInsertInput" method="get" class="col-6 pl-0">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <form action="adminInsertComplete" method="get" class="col-6 pr-0">
            <button type="submit" class="btn btn-primary btn-block">登録する</button>
        </form>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/user/member_footer.jsp" %>
</body>
</html>