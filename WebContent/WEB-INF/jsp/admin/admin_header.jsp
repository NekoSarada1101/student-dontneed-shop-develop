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
    <nav class="navbar navbar-expand-lg bg-success px-1 px-md-2 px-lg-4">
        <a href="adminTop" class="navbar-brand text-white mr-3" style="width: 160px">KIK</a>

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
