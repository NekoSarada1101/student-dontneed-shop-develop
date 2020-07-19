<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String errorMessage = (String) session.getAttribute("errorMessage");
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者情報変更入力</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/admin/admin_header.jsp" %>

<h1 class="mt-3 text-center">管理者情報変更入力</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">

        <%if (errorMessage != null) { %>
        <div class="alert alert-danger mt-4">
            <%=errorMessage%>
        </div>
        <% } %>

        <form action="adminUpdateCheck" method="post" class="col-12 mx-auto" id="form">
            <div class="form-group row">
                <label for="adminMail" class="col-12"><strong>メールアドレス</strong></label>
                <input type="email" value="<%=adminBeans.getAdminMail()%>" class="form-control col-12" id="adminMail"
                       name="adminMail" maxlength="100" required>
            </div>
            <div class="form-group row">
                <label for="adminPassword"><strong>パスワード</strong></label>
                <input type="password" class="form-control col-12" id="adminPassword" name="adminPassword"
                       maxlength="128"
                       required>
            </div>
            <div class="form-group row">
                <label for="name" class="col-12"><strong>名前</strong></label>
                <input type="text" value="<%=adminBeans.getAdminName()%>" class="form-control col-6" id="name"
                       name="adminName"
                       maxlength="20" required>
            </div>
            <div class="form-group row">
                <label for="postalCode" class="col-12"><strong>郵便番号</strong></label>
                <input type="text" value="<%=adminBeans.getPostalCode()%>" class="form-control col-4" id="postalCode"
                       name="postalCode" placeholder="ハイフン無し" pattern="\d{7}" title="郵便番号をハイフン無しで入力してください" required onKeyUp="AjaxZip3.zip2addr(this,'','address','address');">
            </div>
            <div class="form-group row">
                <label for="address" class="col-12"><strong>住所</strong></label>
                <input type="text" value="<%=adminBeans.getAddress()%>" class="form-control col-12" id="address"
                       name="address" maxlength="50" required>
            </div>
        </form>

        <form action="adminDetail" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="col-6">
            <button type="submit" class="btn btn-primary btn-block mr-auto" form="form">変更</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
