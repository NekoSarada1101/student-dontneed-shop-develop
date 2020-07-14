<<<<<<< Updated upstream
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
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>

<%

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録入力</title>
<%@include file="/WEB-INF/jsp/bootstrap.jsp"%>
</head>

<body>
<%@include file="/WEB-INF/jsp/user/member_header.jsp" %>

<h1 class="mt-3 text-center">会員情報登録入力</h1>
<div class="row  mt-3">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4 row mx-auto">
        <form action="productInsertCheck" method="post" class="row mx-auto" id="form" enctype=multipart/form-data>
            <div class="form-group col-12">
                <label for="productName"><strong>メールアドレス</strong></label>
                <input type="text" value="<%=productBeans.getProductName()%>" class="form-control" id="productName"
                       name="productName" maxlength="30" required>
            </div>
            <div class="form-group col-6">
                <label for="price"><strong>氏名</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
            <div class="form-group col-12">
                <label for="image"><strong>パスワード</strong></label>
                <input type="file" class="form-control" id="image" name="image" onchange="previewImage(this);"
                       accept="image/*" required>
                <div>
                    Preview:<br>
                    <img class="position-static" id="preview" src="getImage" style="max-width:400px;">
                </div>
            </div>
            <div class="form-group col-12">
                <label for="productExplanation"><strong>クレジットカード</strong></label>
                <textarea class="form-control" id="productExplanation"
                          name="productExplanation" rows="10"
                          cols="40" maxlength="400" required><%=productBeans.getProductExplanation()%></textarea>
            </div>
            <div class="form-group col-6">
                <labal for="genre"><strong>住所</strong></labal>
                <select class="custom-select form-control" id="genre" name="genre">
                    <% for (Map<String, Object> genreInfoMap : genreInfoList) { %>
                    <option value="<%=genreInfoMap.get("genreCode")%>">
                        <%=genreInfoMap.get("genreName")%>
                    </option>
                    <% } %>
                </select>
            </div>
             <div class="form-group col-6">
                <label for="price"><strong>郵便番号</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
             <div class="form-group col-6">
                <label for="price"><strong>電話番号</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
             <div class="form-group col-6">
                <label for="price"><strong>有効期限</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
             <div class="form-group col-6">
                <label for="price"><strong>セキュリティコード</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
             <div class="form-group col-6">
                <label for="price"><strong>名義人</strong></label>
                <input type="number" value="<%=productBeans.getPrice()%>" class="form-control" id="price" name="price"
                       maxlength="16" required>
            </div>
        </form>

        <form action="adminTop" method="get" class="col-6">
            <button type="submit" class="btn btn-outline-dark btn-block">戻る</button>
        </form>

        <div class="px-3 col-6">
            <button type="submit" class="btn btn-primary btn-block" form="form">登録</button>
        </div>
    </div>
</div>






</body>
</html>
>>>>>>> Stashed changes
