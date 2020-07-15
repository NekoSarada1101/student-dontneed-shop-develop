<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報登録確認</title>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
     <link rel="stylesheet" href="css/member_header.css">
</head>

<body>
<header class="sticky-top">
    <nav class="nav navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="memberTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>
    </nav>
</header>

<h1 class="mt-3 text-center">会員情報登録確認</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
		<table class="table table-striped">
		<tbody>
			<tr class="row">
       			 <th class="col-3">メールアドレス</th>
       			 <td class="col-9">
        		    <%= memberBeans.getMemberMail() %>
       			 </td>
  			</tr>

    		<tr class="row">
        		<th class="col-3">パスワード</th>
        		<td class="col-9">
        		 ●●●●●●●●
        		 </td>
		    </tr>

 			<tr class="row">
        		<th class="col-3">名前</th>
        		<td class="col-9">
            		<%= memberBeans.getMemberName() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">郵便番号</th>
        		<td class="col-9">
            		<%= memberBeans.getPostalCode() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">住所</th>
        		<td class="col-9">
            		<%= memberBeans.getAddress() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">電話番号</th>
        		<td class="col-9">
            		<%= memberBeans.getTell() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">クレジット番号</th>
        		<td class="col-9">
            		<%= memberBeans.getCreditCard() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">有効期限</th>
        		<td class="col-9">
            		<%= memberBeans.getExpirationDate() %>
        		</td>
    		</tr>

    		<tr class="row">
        		<th class="col-3">名義者名</th>
        		<td class="col-9">
            		<%= memberBeans.getHolder() %>
        		</td>
    		</tr>
    		<tr class="row">
        		<th class="col-3">セキュリティコード</th>
        		<td class="col-9">
            		<%= memberBeans.getSecurityCode() %>
        		</td>
    		</tr>
		</table>

		<form action="memberInsertInput" method="get" class="col-6">
    		<button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
		</form>

		<form action="memberInsertComplete" method="get" class="col-6">
    		<button type="submit" class="btn btn-primary btn-block">登録する</button>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/user/member_footer.jsp" %>
</body>
</html>

