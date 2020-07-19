<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String errorMessage = (String) session.getAttribute("errorMessage");
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報変更入力</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h1 class="my-5 text-center">会員情報変更入力</h1>

<div class="row">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <%if (errorMessage != null) { %>
        <div class="alert alert-danger col-12">
            <%=errorMessage%>
        </div>
        <% } %>

        <form action="memberUpdateCheck" method="post" class="col-12 mx-auto" id="form">
            <div class="form-group row">
                <label for="memberMail" class="col-12"><strong>メールアドレス</strong></label>
                <input type="email" value="<%=memberBeans.getMemberMail()%>" class="form-control col-12" id="memberMail"
                       name="memberMail" maxlength="100" required>
            </div>

            <div class="form-group row">
                <label for="memberPassword" class="col-12"><strong>パスワード</strong></label>
                <input type="password" class="form-control col-12" id="memberPassword" name="memberPassword"
                       placeholder="半角英数字"
                       maxlength="128"
                       pattern="[a-zA-Z0-9]+" title="半角英数字で入力してください。"
                       required>
            </div>

            <div class="form-group row">
                <label for="name" class="col-12"><strong>名前</strong></label>
                <input type="text" value="<%=memberBeans.getMemberName()%>" class="form-control col-6" id="name"
                       name="memberName"
                       maxlength="20" required>
            </div>

            <div class="form-group row">
                <label for="postalCode" class="col-12"><strong>郵便番号</strong></label>
                <input type="text" value="<%=memberBeans.getPostalCode()%>" class="form-control col-4" id="postalCode"
                       name="postalCode" placeholder="ハイフン無し" pattern="\d{7}" title="郵便番号をハイフン無しで入力してください" maxlength="7" required
                       onKeyUp="AjaxZip3.zip2addr(this,'','address','address');">
            </div>

            <div class="form-group row">
                <label for="address" class="col-12"><strong>住所</strong></label>
                <input type="text" value="<%=memberBeans.getAddress()%>" class="form-control col-12" id="address"
                       name="address" maxlength="50" required>
            </div>

            <div class="form-group row">
                <label for="tell" class="col-12"><strong>電話番号</strong></label>
                <input type="tel" value="<%=memberBeans.getTell()%>" class="form-control col-6" id="tell" name="tell"
                       pattern="[0-9]+" title="電話番号をハイフン無しで入力してください"
                       maxlength="11" placeholder="ハイフン無し" required>
            </div>

            <div class="form-group row">
                <label for="creditCard" class="col-12"><strong>クレジットカード</strong></label>
                <input type="text" value="<%=memberBeans.getCreditCard()%>" class="form-control col-6" id="creditCard"
                       name="creditCard" pattern="\d{16}" title="クレジットカード番号をハイフン無しで入力してください" maxlength="16" placeholder="ハイフン無し"
                       required>
            </div>

            <div class="form-group row">
                <label for="expirationDate" class="col-12"><strong>有効期限</strong></label>
                <input type="date" value="<%=memberBeans.getExpirationDate()%>" class="form-control col-6"
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
                <input type="text" value="<%=memberBeans.getSecurityCode()%>" class="form-control col-3"
                       id="securityCode"
                       name="securityCode" pattern="\d{3}" title="3桁のセキュリティコードを入力してください" maxlength="3" required>
            </div>
        </form>

        <form action="memberDetail" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="col-6">
            <button type="submit" class="btn btn-primary btn-block mr-auto" form="form">変更</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
