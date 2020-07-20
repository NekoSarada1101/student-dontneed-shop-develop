<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.service.CommonService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");

    CommonService commonService = new CommonService();
    String memberMail = commonService.escapeProcess(memberBeans.getMemberMail());
    String postalCode = commonService.escapeProcess(memberBeans.getPostalCode());
    String address = commonService.escapeProcess(memberBeans.getAddress());
    String creditCard = commonService.escapeProcess(memberBeans.getCreditCard());
    String expirationDate = commonService.escapeProcess(memberBeans.getExpirationDate());
    String holder = commonService.escapeProcess(memberBeans.getHolder());
    String securityCode = commonService.escapeProcess(memberBeans.getSecurityCode());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h2 class="text-center mt-2">会員情報</h2>

<div class="row">
    <div class="row col-8 col-md-4 mx-auto">
        <ul class="list-group list-group-flush col-12">
            <li class="list-group-item">
                <label for="memberMail"><strong>会員メール</strong></label>
                <p id="memberMail">
                    <%=memberMail%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="memberPassword"><strong>パスワード</strong></label>
                <p id="memberPassword">●●●●●●●●</p>
            </li>
            <li class="list-group-item">
                <label for="memberName"><strong>会員名</strong></label>
                <p id="memberName">
                    <%=memberName%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="postalCode"><strong>郵便番号</strong></label>
                <p id="postalCode">
                    <%=postalCode%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="address"><strong>住所</strong></label>
                <p id="address">
                    <%=address%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="creditCard"><strong>クレジットカード番号</strong></label>
                <p id="creditCard">
                    <%=creditCard%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="expirationDate"><strong>有効期限</strong></label>
                <p id="expirationDate">
                    <%=expirationDate%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="holder"><strong>名義人</strong></label>
                <p id="holder">
                    <%=holder%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="securityCode"><strong>セキュリティコード</strong></label>
                <p id="securityCode">
                    <%=securityCode%>
                </p>
            </li>
        </ul>

        <form action="memberUpdateInput" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-info btn-block btn-lg">変更</button>

        </form>

        <form action="memberDeleteCheck" method="post" class="col-6 text-center">
            <button type="submit" class="btn btn-danger btn-block btn-lg">退会</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
