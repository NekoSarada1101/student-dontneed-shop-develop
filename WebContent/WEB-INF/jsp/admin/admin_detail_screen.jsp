<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者詳細画面</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="mt-3 text-center">管理者詳細画面</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <table class="table table-striped">
            <tbody>
            <tr class="row">
                <th class="col-3">名前</th>
                <td class="col-9">
                    <%=adminBeans.getAdminName()%>
                </td>
            </tr>
            <tr class="row">
                <th class="col-3">パスワード</th>
                <td class="col-9">
                    <%=adminBeans.getAdminPassword()%>
                </td>
            </tr>
            <tr class="row">
                <th class="col-3">メールアドレス</th>
                <td id="square-image" class="col-9">
                    <%=adminBeans.getAdminMail() %>
                </td>
            </tr>
            <tr class="row">
                <th class="col-3">郵便番号</th>
                <td class="col-9">
                    <%=adminBeans.getPostalCode()%>
                </td>
            </tr>
            <tr class="row">
                <th class="col-3">住所</th>
                <td class="col-9">
                    <%=adminBeans.getAddress()%>
                </td>
            </tr>
            </tbody>
        </table>

        <form action="adminInsertInput" method="post" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">トップへ戻る</button>
        </form>

    </div>
</div>

<%@ include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<script>
    window.onload = imageResizeFunc;
    window.addEventListener("resize", imageResizeFunc);

    function imageResizeFunc() {
        var width = document.getElementById('square-image').offsetWidth;
        console.log(width)
        width = String(width) + "px";
        document.getElementById("square-image").style.height = width;
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
