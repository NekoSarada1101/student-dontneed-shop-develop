<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h2 class="text-center mt-2">管理者情報</h2>

<div class="row">
    <div class="row col-8 col-md-4 mx-auto">
        <ul class="list-group list-group-flush col-12">
            <li class="list-group-item">
                <label for="adminMail"><strong>管理者メール</strong></label>
                <p id="adminMail">
                    <%=adminBeans.getAdminMail()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="adminName"><strong>管理者名</strong></label>
                <p id="adminName">
                    <%=adminBeans.getAdminName()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="adminPassword"><strong>パスワード</strong></label>
                <p id="adminPassword">●●●●●●●●</p>
            </li>
            <li class="list-group-item">
                <label for="postalCode"><strong>郵便番号</strong></label>
                <p id="postalCode">
                    <%=adminBeans.getPostalCode()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="address"><strong>住所</strong></label>
                <p id="address">
                    <%=adminBeans.getAddress()%>
                </p>
            </li>
        </ul>

        <form action="adminUpdateInput" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-info btn-lg">変更</button>
        </form>


        <form action="adminDeleteCheck" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-danger btn-lg">退会</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
