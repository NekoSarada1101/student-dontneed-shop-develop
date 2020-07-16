<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
    if (memberBeans == null) {
        memberBeans = new MemberBeans();
        memberBeans.setMemberMail("");
        memberBeans.setMemberName("");
        memberBeans.setPostalCode("");
        memberBeans.setAddress("");
        memberBeans.setTell("");
        memberBeans.setCreditCard("");
        memberBeans.setExpirationDate("");
        memberBeans.setHolder("");
        memberBeans.setSecurityCode("");
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
        <span href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</span>
    </nav>
</header>

<h1 class="mt-3 text-center">会員情報登録入力</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">

        <%if (errorMessage != null) { %>
        <div class="alert alert-danger mt-4">
            <%=errorMessage%>
        </div>
        <% } %>

        <form action="memberInsertCheck" method="post" class="col-12 mx-auto" id="form">
            <div class="form-group row">
                <label for="memberMail" class="col-12"><strong>メールアドレス</strong></label>
                <input type="text" value="<%=memberBeans.getMemberMail()%>" class="form-control col-12" id="memberMail"
                       name="memberMail" maxlength="100" required>
            </div>
            <div class="form-group row">
                <label for="memberPassword"><strong>パスワード</strong></label>
                <input type="password" class="form-control col-12" id="memberPassword" name="memberPassword"
                       maxlength="128"
                       required>
            </div>
            <div class="form-group row">
                <label for="name" class="col-12"><strong>名前</strong></label>
                <input type="text" value="<%=memberBeans.getMemberName()%>" class="form-control col-6" id="name"
                       name="name"
                       maxlength="20" required>
            </div>
            <div class="form-group row">
                <label for="postalCode" class="col-12"><strong>郵便番号</strong></label>
                <input type="number" value="<%=memberBeans.getPostalCode()%>" class="form-control col-4" id="postalCode"
                       name="postalCode" minlength="7" maxlength="7" required>
            </div>
            <div class="form-group row">
                <label for="address" class="col-12"><strong>住所</strong></label>
                <input type="text" value="<%=memberBeans.getAddress()%>" class="form-control col-12" id="address"
                       name="address" maxlength="50" required>
            </div>
            <div class="form-group row">
                <label for="tell" class="col-12"><strong>電話番号</strong></label>
                <input type="number" value="<%=memberBeans.getTell()%>" class="form-control col-6" id="tell" name="tell"
                       maxlength="11" required>
            </div>
            <div class="form-group row">
                <label for="creditCard" class="col-12"><strong>クレジットカード</strong></label>
                <input type="number" value="<%=memberBeans.getCreditCard()%>" class="form-control col-6" id="creditCard"
                       name="creditCard" minlength="16" maxlength="16" required>
            </div>
            <div class="form-group row">
                <label for="expirationDate" class="col-12"><strong>有効期限</strong></label>
                <input type="date" value="<%=memberBeans.getExpirationDate()%>" class="form-control col-4"
                       id="expirationDate"
                       name="expirationDate" required>
            </div>
            <div class="form-group row">
                <label for="holder" class="col-12"><strong>名義人名</strong></label>
                <input type="text" value="<%=memberBeans.getHolder()%>" class="form-control col-6" id="holder"
                       name="holder"
                       maxlength="20" required>
            </div>
            <div class="form-group row">
                <label for="securityCode" class="col-12"><strong>セキュリティコード</strong></label>
                <input type="number" value="<%=memberBeans.getSecurityCode()%>" class="form-control col-3"
                       id="securityCode"
                       name="securityCode" minlength="3" maxlength="3" required>
            </div>
        </form>

        <form action="memberLogin" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="col-6">
            <button type="submit" class="btn btn-primary btn-block mr-auto" form="form">登録</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
