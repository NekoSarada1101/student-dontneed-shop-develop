<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="shop.model.bean.MemberBeans"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<% MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");%>

<body>

<h2>入力フォーム</h2>
<form action="" method="post">
    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td> <%= memberBeans.getMemberMail() %> </td>
        </tr>

        <tr>
            <th>名前</th>
            <td> <%= memberBeans.getMemberName() %> </td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td> <%= memberBeans.getMemberPassword() %> </td>
        </tr>

        <tr>
            <th>住所</th>
            <td> <%= memberBeans.getAddress() %> </td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td> <%= memberBeans.getPostalCode() %> </td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td> <%= memberBeans.getTell() %> </td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td> <%= memberBeans.getCreditCard() %> </td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td> <%= memberBeans.getExpirationDate() %> </td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td> <%= memberBeans.getSecurityCard() %> </td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td> <%= memberBeans.getHolder() %> </td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

      <a href="/student_dontneed_shop_dev/WebContent/WEB-INF/jsp/member/member_top.jsp">戻る</a><br>

</body>
</html>

