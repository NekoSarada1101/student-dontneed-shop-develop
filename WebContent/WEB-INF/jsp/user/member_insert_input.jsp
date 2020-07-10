<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
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
            <td><input type="text" name="memberMail"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="password" name="memberPassword"></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" name="memberName"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="number" name="postalCode"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" name="address"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="tel" name="tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="number" name="creditCard"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="date" name="expirationDate"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" name="holder"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="number" name="securityCode"></td>
        </tr>
    </table>

    <input type="submit" value="確認">
    <input type="submit" value="登録">
</form>

<form action="memberLogin" method="get">
    <input type="submit" value="戻る">
</form>

</body>
</html>
