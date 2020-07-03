<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="shop.model.bean.MemberBeans"%>

    <% String errorMessage = (String)session.getAttribute("errorMessage"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");

	if (memberBeans = nall ){
		%>

		<body>



<h2>入力フォーム</h2>
<form action="" method="post">



    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text"  name = "member_mail" ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" name = "member_name"></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="text" name = "member_password"></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" name = "address"></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="text" name = "postal_code"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text"name = "tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" name = "credit_card"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" name = "deadline"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" name = "security_code"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" name = "security_name"></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

      <a href="/student_dontneed_shop_dev/WebContent/WEB-INF/jsp/member/member_login.jsp">戻る</a><br>

</body>

		<%
	}else{
		%>


<body>



<h2>入力フォーム</h2>
<form action="" method="post">

	<p> <%=errorMessage %> </p>

    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" value= <%=memberBeans.getMemberMail() %> name = "member_mail" ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" value= <%=memberBeans.getMemberName() %>name = "member_name"></td>
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
            <td><input type="text" value= <%=memberBeans.getPostalCode() %>name = "postal_code"></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text" value= <%=memberBeans.getTell() %>name = "tell"></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" value= <%=memberBeans.getCreditCard() %>name = "credit_card"></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" value= <%=memberBeans.getExpirationDate() %>name = "deadline"></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" value= <%=memberBeans.getSecurityCode() %>name = "security_code"></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" value= <%=memberBeans.getHolder() %>name = "security_name"></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

      <a href="/student_dontneed_shop_dev/WebContent/WEB-INF/jsp/member/member_login.jsp">戻る</a><br>

</body>

<%
	}
%>
</html>