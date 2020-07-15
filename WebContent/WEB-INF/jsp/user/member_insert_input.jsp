<<<<<<< Updated upstream
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="java.util.List" %>


<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
    if (memberBeans == null) {
        memberBeans = new MemberBeans();
        memberBeans.setMemberMail("");
        memberBeans.setMemberName("");
        memberBeans.setAddress("");
        memberBeans.setHolder("");
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録入力</title>
<%@include file="/WEB-INF/jsp/bootstrap.jsp"%>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h1 class="mt-3 text-center">会員情報登録入力</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <form action="memberInsertCheck" method="post" class="row mx-auto" id="form" enctype=multipart/form-data>

            <div class="form-group col-12">
                <label for="memberMail"><strong>メールアドレス</strong></label>
                <input type="text" value="<%=memberBeans.getMemberMail()%>" class="form-control" id="memberMail"
                       name="memberMail" maxlength="30" required>
            </div>

            <div class="form-group col-6">
                <label for="memberName"><strong>氏名</strong></label>
                <input type="text" value="<%=memberBeans.getMemberName()%>" class="form-control" id="memberName" name="memberName"
                       maxlength="20" required>
            </div>

            <div class="form-group col-12">
                <label for="memberPassword"><strong>パスワード</strong></label>
                <input type="password" value="<%=memberBeans.getMemberPassword()%>"class="form-control" id="memberPassword" name="memberPassword"
                       maxlength="128" required>
            </div>

            <div class="form-group col-12">
                <label for="creditCard"><strong>クレジットカード</strong></label>
                <input type="text" value="<%=memberBeans.getCreditCard()%>"class="form-control" id="creditCard" name="creditCard"
                       maxlength="16" required>
            </div>

            <div class="form-group col-6">
                <label for="address"><strong>住所</strong></label>
                <input type="text" value="<%=memberBeans.getAddress()%>"class="form-control" id="address" name="address"
                       maxlength="50" required>
            </div>

            <div class="form-group col-6">
                <label for="postalCode"><strong>郵便番号</strong></label>
                <input type="number" value="<%=memberBeans.getPostalCode()%>" class="form-control" id="price" name="price"
                       maxlength="7" required>
            </div>

             <div class="form-group col-6">
                <label for="tell"><strong>電話番号</strong></label>
                <input type="tel" value="<%=memberBeans.getTell()%>" class="form-control" id="price" name="price"
                       maxlength="11" required>
            </div>

             <div class="form-group col-6">
                <label for="expirationDate"><strong>有効期限</strong></label>
                <input type="date" value="<%=memberBeans.getExpirationDate()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>

             <div class="form-group col-6">
                <label for="securityCode"><strong>セキュリティコード</strong></label>
                <input type="number" value="<%=memberBeans.getSecurityCode()%>" class="form-control" id="price" name="price"
                       maxlength="3" required>
            </div>

             <div class="form-group col-6">
                <label for="holder"><strong>名義人</strong></label>
                <input type="text" value="<%=memberBeans.getHolder()%>" class="form-control" id="price" name="price"
                       maxlength="20" required>
            </div>

        </form>

        <form action="memberTop" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="px-3 col-6">
            <button type="submit" class="btn btn-primary btn-block" form="form">登録</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<script>
    function previewImage(obj) {
        var fileReader = new FileReader();
        fileReader.onload = (function () {
            document.getElementById('preview').src = fileReader.result;
        });
        fileReader.readAsDataURL(obj.files[0]);
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>




</body>
</html>
>>>>>>> Stashed changes
