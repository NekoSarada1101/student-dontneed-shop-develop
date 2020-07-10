<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報登録確認</title>
</head>

<body>
<h2>会員情報登録確認</h2>
<table border="1">
    <tr>
        <th>メールアドレス</th>
        <td>
            <%= memberBeans.getMemberMail() %>
        </td>
    </tr>

    <tr>
        <th>パスワード</th>
        <td> ●●●●●●●●</td>
    </tr>

    <tr>
        <th>名前</th>
        <td>
            <%= memberBeans.getMemberName() %>
        </td>
    </tr>

    <tr>
        <th>郵便番号</th>
        <td>
            <%= memberBeans.getPostalCode() %>
        </td>
    </tr>

    <tr>
        <th>住所</th>
        <td>
            <%= memberBeans.getAddress() %>
        </td>
    </tr>

    <tr>
        <th>電話番号</th>
        <td>
            <%= memberBeans.getTell() %>
        </td>
    </tr>

    <tr>
        <th>クレジット番号</th>
        <td>
            <%= memberBeans.getCreditCard() %>
        </td>
    </tr>

    <tr>
        <th>有効期限</th>
        <td>
            <%= memberBeans.getExpirationDate() %>
        </td>
    </tr>

    <tr>
        <th>名義者名</th>
        <td>
            <%= memberBeans.getHolder() %>
        </td>
    </tr>
    <tr>
        <th>セキュリティコード</th>
        <td>
            <%= memberBeans.getSecurityCode() %>
        </td>
    </tr>
</table>

<form action="memberInsertComplete" method="get">
    <input type="submit" value="登録">
</form>

<form action="memberInsertInput" method="post">
    <input type="submit" value="戻る">
</form>
</body>
</html>

