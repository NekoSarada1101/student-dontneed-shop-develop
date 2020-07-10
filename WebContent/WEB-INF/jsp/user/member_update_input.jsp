<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% String errorMessage = (String)session.getAttribute("errorMessage"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% MemberBeans memberBeans = (MemberBeans)session.getAttribute("memberBeans");%>
<body>



<h2>入力フォーム</h2>
<form action="memberUpdateCheck" method="post">

	<p> <%=errorMessage %> </p>

    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" value= <%=memberBeans.getMemberMail() %> name="memberMail" ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" value= <%=memberBeans.getMemberName() %>name="memberName"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="text" value= <%=memberBeans.getMemberPassword() %>name="memberPassword"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" value= <%=memberBeans.getAddress() %>name="address"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="text" value= <%=memberBeans.getPostalCode() %>name="postalCode"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text" value= <%=memberBeans.getTell() %>name="tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" value= <%=memberBeans.getCreditCard() %>name="creditCard"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" value= <%=memberBeans.getExpirationDate() %>name="expirationDate"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" value= <%=memberBeans.getSecurityCard() %>name="securityCard"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" value= <%=memberBeans.getHolder() %>name="holder"></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>
<form action="memberTop" method="post">

     <input type="submit" value="トップへ戻る">

</form>
</body>
</html>