<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");
    if (adminBeans == null) {
        adminBeans = new AdminBeans();
        adminBeans.setAdminMail("");
        adminBeans.setAdminName("");
        adminBeans.setPostalCode("");
        adminBeans.setAddress("");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報登録入力</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-3">
        <span class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<h1 class="my-5 text-center">管理者情報登録入力</h1>

<div class="row">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <%if (errorMessage != null) { %>
        <div class="alert alert-danger col-12">
            <%=errorMessage%>
        </div>
        <% } %>

        <form action="adminInsertCheck" method="post" class="col-12 mx-auto" id="form">
            <div class="form-group row">
                <label for="adminMail" class="col-12"><strong>メールアドレス</strong></label>
                <input type="email" value="<%=adminBeans.getAdminMail()%>" class="form-control col-12" id="adminMail"
                       name="adminMail" maxlength="100" required>
            </div>

            <div class="form-group row">
                <label for="adminPassword"><strong>パスワード</strong></label>
                <input type="password" class="form-control col-12" id="adminPassword" name="adminPassword"
                       placeholder="半角英数字"
                       maxlength="128"
                       pattern="[a-zA-Z0-9]+" title="半角英数字で入力してください。"
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
                       name="postalCode" placeholder="ハイフン無し" pattern="\d{7}" title="郵便番号をハイフン無しで入力してください" maxlength="7"
                       required
                       onKeyUp="AjaxZip3.zip2addr(this,'','address','address');">
            </div>

            <div class="form-group row">
                <label for="address" class="col-12"><strong>住所</strong></label>
                <input type="text" value="<%=adminBeans.getAddress()%>" class="form-control col-12" id="address"
                       name="address" maxlength="50" required>
            </div>
        </form>

        <form action="adminLogin" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="col-6">
            <button type="submit" class="btn btn-primary btn-block mr-auto" form="form">登録</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
