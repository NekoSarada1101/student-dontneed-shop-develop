<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ログイン</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-3">
        <span href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<div>
    <h1 class="text-center my-5">会員ログイン</h1>
    <form action="memberLogin" method="POST" class="col-10 col-sm-8 col-md-6 col-lg-3 mx-auto">
        <div class="form-group">
            <label for="memberMail"><strong>メールアドレス</strong></label>
            <input type="text" class="form-control" id="memberMail" name="memberMail">
        </div>
        <div class="form-group">
            <label for="memberPassword"><strong>パスワード</strong></label>
            <input type="password" class="form-control" id="memberPassword" name="memberPassword">
        </div>

        <%if (errorMessage != null) { %>
        <div class="alert alert-danger mt-4">
            <%=errorMessage%>
        </div>
        <% } %>

        <button type="submit" class="btn btn-primary btn-block mt-5">ログイン</button>
    </form>
</div>

<%@include file="/WEB-INF/jsp/admin/admin_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>