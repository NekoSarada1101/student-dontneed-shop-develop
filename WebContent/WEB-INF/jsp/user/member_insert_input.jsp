<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
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
</head>

<body>
<h2>会員情報登録入力</h2>
<form action="memberInsertCheck" method="post">
    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" name="memberMail" value="<%=memberBeans.getMemberMail()%>"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="password" name="memberPassword"></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" name="memberName" value="<%=memberBeans.getMemberName()%>"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="number" name="postalCode"
                       value="<%if (memberBeans.getPostalCode() != 0) %><%=memberBeans.getPostalCode()%>"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" name="address" value="<%=memberBeans.getAddress()%>"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="number" name="tell" value="<%if (memberBeans.getTell() != 0) %><%=memberBeans.getTell()%>">
            </td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="number" name="creditCard"
                       value="<%if (memberBeans.getCreditCard() != 0) %><%=memberBeans.getCreditCard()%>"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="date" name="expirationDate"
                       value="<%if (memberBeans.getExpirationDate() != null) %><%=memberBeans.getExpirationDate()%>">
            </td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" name="holder" value="<%=memberBeans.getHolder()%>"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="number" name="securityCode"
                       value="<%if (memberBeans.getSecurityCode() != 0) %><%=memberBeans.getSecurityCode()%>"></td>
        </tr>
    </table>

    <%if (errorMessage != null) { %>
    <%=errorMessage%>
    <% } %>

    <input type="submit" value="登録">
</form>

<form action="memberLogin" method="get">
    <input type="submit" value="戻る">
</form>

</body>
</html>
