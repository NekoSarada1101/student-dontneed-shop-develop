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
</head>

<body>
<h2>入力フォーム</h2>
<form action="memberUpdateCheck" method="post">
    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" value="<%=memberBeans.getMemberMail() %>" name="memberMail"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="password" value="<%=memberBeans.getMemberPassword() %>" name="memberPassword"></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" value="<%=memberBeans.getMemberName() %>" name="memberName"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="number" value="<%=memberBeans.getPostalCode() %>" name="postalCode"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" value="<%=memberBeans.getAddress() %>" name="address"></td>
        </tr>


        <tr>
            <th>電話番号</th>
            <td><input type="number" value="<%=memberBeans.getTell() %>" name="tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="number" value="<%=memberBeans.getCreditCard() %>" name="creditCard"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="date" value="<%=memberBeans.getExpirationDate() %>" name="expirationDate"></td>
        </tr>

        <tr>
            <th>名義人名</th>
            <td><input type="text" value="<%=memberBeans.getHolder() %>" name="holder"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="number" value="<%=memberBeans.getSecurityCode() %>" name="securityCode"></td>
        </tr>
    </table>

    <%if (errorMessage != null) { %>
    <%=errorMessage%>
    <% } %>

    <input type="submit" value="変更">
</form>

<form action="memberDetail" method="get">
    <input type="submit" value="戻る">
</form>

</body>
</html>
