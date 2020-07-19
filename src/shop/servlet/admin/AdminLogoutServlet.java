package shop.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogout")
public class AdminLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("adminLoginInfo");
        session.removeAttribute("index");
        session.removeAttribute("productBeans");
        session.removeAttribute("productList");

        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp");
    }
}

