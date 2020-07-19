package shop.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberLogout")
public class MemberLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("memberLoginInfo");
        session.removeAttribute("productList");
        session.removeAttribute("productBeans");
        session.removeAttribute("memberBeans");

        request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp");
    }
}
