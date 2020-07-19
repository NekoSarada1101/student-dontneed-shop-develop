package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.service.ErrorCheckService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberLogout")
public class MemberLogoutServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        HttpSession session = request.getSession();
        session.removeAttribute("memberLoginInfo");
        session.removeAttribute("productList");
        session.removeAttribute("productBeans");
        session.removeAttribute("memberBeans");

        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request,response);
    }
}
