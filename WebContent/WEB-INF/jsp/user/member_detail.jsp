<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");
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
                    <%=memberBeans.getMemberMail()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="memberName"><strong>会員名</strong></label>
                <p id="memberName">
                    <%=memberBeans.getMemberName()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="memberPassword"><strong>パスワード</strong></label>
                <p id="memberPassword">●●●●●●●●</p>
            </li>
            <li class="list-group-item">
                <label for="postalCode"><strong>郵便番号</strong></label>
                <p id="postalCode">
                    <%=memberBeans.getPostalCode()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="address"><strong>住所</strong></label>
                <p id="address">
                    <%=memberBeans.getAddress()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="creditCard"><strong>クレジットカード番号</strong></label>
                <p id="creditCard">
                    <%=memberBeans.getCreditCard()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="expirationDate"><strong>有効期限</strong></label>
                <p id="expirationDate">
                    <%=memberBeans.getExpirationDate()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="holder"><strong>名義人</strong></label>
                <p id="holder">
                    <%=memberBeans.getHolder()%>
                </p>
            </li>
            <li class="list-group-item">
                <label for="securityCode"><strong>セキュリティコード</strong></label>
                <p id="securityCode">
                    <%=memberBeans.getSecurityCode()%>
                </p>
            </li>
        </ul>

        <form action="memberUpdateInput" method="get" class="col-6 text-center">
            <button type="submit" class="btn btn-info btn-lg">変更</button>

        </form>

        <form action="memberDeleteCheck" method="post" class="col-6 text-center">
            <button type="submit" class="btn btn-danger btn-lg">退会</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/user/member_footer.jsp" %>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
