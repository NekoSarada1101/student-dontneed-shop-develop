<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="shop.model.bean.MemberBeans"%>

    <% String errorMessage = (String)session.getAttribute("errorMessage"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録入力</title>
</head>
<% MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");

	if (memberBeans = nall ){
		%>

		<body>



<h2>会員情報入力</h2>
<form action="memberInsertCheck" method="post">



    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text"  name = "memberMail" ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" name = "memberName"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="text" name = "memberPassword"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" name = "address"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="text" name = "postalCode"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text"name = "tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" name = "creditCard"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" name = "deadline"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" name = "securityCode"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" name = "securityName"></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

<form action="memberLogin" method="get">
      <input type="submit" value="戻る">
</form>

</body>

		<%
	}else{
		%>


<body>



<h2>会員情報入力</h2>
<form action="memberInsertCheck" method="post">

	<p> <%=errorMessage %> </p>

    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" value= <%=memberBeans.getMemberMail() %> name = "memberMail" ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" value= <%=memberBeans.getMemberName() %>name = "memberName"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="password" name = "member_password"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" value= <%=memberBeans.getAddress() %>name = "address"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="text" value= <%=memberBeans.getPostalCode() %>name = "postalCode"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text" value= <%=memberBeans.getTell() %>name = "tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" value= <%=memberBeans.getCreditCard() %>name = "creditCard"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" value= <%=memberBeans.getExpirationDate() %>name = "deadline"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" value= <%=memberBeans.getSecurityCode() %>name = "securityCode"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" value= <%=memberBeans.getHolder() %>name = "securityName"></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

<form action="memberLogin" method="get">
      <input type="submit" value="戻る">
</form>

</body>

<%
	}
%>
</html>