<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.service.ErrorCheckService" %>
<%
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");

    String memberMail = ErrorCheckService.escapeProcess(memberBeans.getMemberMail());
    String memberName = ErrorCheckService.escapeProcess(memberBeans.getMemberName());
    String postalCode = ErrorCheckService.escapeProcess(memberBeans.getPostalCode());
    String address = ErrorCheckService.escapeProcess(memberBeans.getAddress());
    String tell = ErrorCheckService.escapeProcess(memberBeans.getTell());
    String creditCard = ErrorCheckService.escapeProcess(memberBeans.getCreditCard());
    String expirationDate = ErrorCheckService.escapeProcess(memberBeans.getExpirationDate());
    String holder = ErrorCheckService.escapeProcess(memberBeans.getHolder());
    String securityCode = ErrorCheckService.escapeProcess(memberBeans.getSecurityCode());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報登録確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/member_header.css">
</head>

<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="memberTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>
    </nav>
</header>

<h1 class="my-5 text-center">会員情報登録確認</h1>

<div class="row">
    <div class="col-11 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <table class="table table-striped border">
            <tbody>
            <tr class="row">
                <th class="col-4">メールアドレス</th>
                <td class="col-8">
                    <%=memberMail%>
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
                    <%=memberName%>
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

            <tr class="row">
                <th class="col-4">電話番号</th>
                <td class="col-8">
                    <%=tell%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">クレジット番号</th>
                <td class="col-8">
                    <%=creditCard%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">有効期限</th>
                <td class="col-8">
                    <%=expirationDate%>
                </td>
            </tr>

            <tr class="row">
                <th class="col-4">名義者名</th>
                <td class="col-8">
                    <%=holder%>
                </td>
            </tr>
            <tr class="row">
                <th class="col-4">セキュリティコード</th>
                <td class="col-8">
                    <%=securityCode%>
                </td>
            </tr>
        </table>

        <form action="memberInsertInput" method="get" class="col-6 pl-0">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <form action="memberInsertComplete" method="get" class="col-6 pr-0">
            <button type="submit" class="btn btn-primary btn-block">登録する</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/user/member_footer.jsp" %>
</body>
</html>

