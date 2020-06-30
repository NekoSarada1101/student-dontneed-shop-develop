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
<form action="" method="post">

	<p> <%=errorMessage %> </p>

    <table border="1">
        <tr>
            <th>メールアドレス</th>
            <td><input type="text" value= <%=memberBeans.getMemberMail() %> ></td>
        </tr>

        <tr>
            <th>名前</th>
            <td><input type="text" value= <%=memberBeans.getMemberName() %>></td>
        </tr>

        <tr>
            <th>パスワード</th>
            <td><input type="text" value= <%=memberBeans.getMemberPassword() %>></td>
        </tr>

        <tr>
            <th>住所</th>
            <td><input type="text" value= <%=memberBeans.getAddress() %>></td>
        </tr>

        <tr>
            <th>郵便番号</th>
            <td><input type="text" value= <%=memberBeans.getPostalCode() %>></td>
        </tr>

        <tr>
            <th>電話番号</th>
            <td><input type="text" value= <%=memberBeans.getTell() %>></td>
        </tr>

        <tr>
            <th>クレジット番号</th>
            <td><input type="text" value= <%=memberBeans.getCreditCard() %>></td>
        </tr>

        <tr>
            <th>有効期限</th>
            <td><input type="text" value= <%=memberBeans.getExpirationDate() %>></td>
        </tr>

        <tr>
            <th>セキュリティコード</th>
            <td><input type="text" value= <%=memberBeans.getSecurityCard() %>></td>
        </tr>

        <tr>
            <th>名義者名</th>
            <td><input type="text" value= <%=memberBeans.getHolder() %>></td>
        </tr>


    </table>

     <input type="submit" value="確認">

    </form>

      <a href="/student_dontneed_shop_dev/WebContent/WEB-INF/jsp/member/member_top.jsp">戻る</a><br>

</body>
</html>