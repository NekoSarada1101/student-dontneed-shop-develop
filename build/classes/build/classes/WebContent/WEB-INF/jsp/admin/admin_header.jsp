<%@ page import="org.omg.CORBA.OBJ_ADAPTER" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.bean.MemberBeans" %>
<%@ page import="shop.model.bean.ProductBeans" %>
<%@ page import="shop.model.bean.AdminBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String adminName = ((AdminBeans) session.getAttribute("adminBeans")).getAdminName();
%>
<!DOCTYPE html>
<html>
<body>
<header class="container-fluid sticky-top">
    <nav class="navbar navbar-expand-lg bg-success px-1 px-sm-3">
        <a href="adminTop" class="text-white navbar-brand px-0 col-5 col-md-4 col-lg-2"><img src="img/logo.png" alt="logo" class="d-inline-block align-top px-0 col-12"></a>
            <%--<strong>S</strong>tudent <br> <strong>D</strong>ont't need <br> <strong>S</strong>hop</a>--%>

        <form action="adminDetail" method="post" class="ml-auto">
            <button type="submit" class="btn btn-warning">
                <%=adminName + "さん"%>
                <br>
                <span>管理者詳細へ</span>
            </button>
        </form>

        <form action="logout" method="get" class="ml-1 ml-sm-3">
            <button type="submit" class="btn btn-dark">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
