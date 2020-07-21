package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    private Logger      logger      = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session    = request.getSession();
        AdminBeans  adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");

        if (adminBeans == null) {
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
        } else {
            logger.trace("{} End", ErrorCheckService.getMethodName());
            response.sendRedirect("adminTop");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        String adminMail     = request.getParameter("adminMail");
        String adminPassword = request.getParameter("adminPassword");

        if (!checkInputTextLength(adminMail, adminPassword)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
            return;
        }

        AdminBeans adminBeans = userService.fetchAdminLogin(adminMail, adminPassword);

        HttpSession session = request.getSession();
        if (adminBeans == null) {
            //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
        } else {
            //取得に成功した場合
            session.setAttribute("adminLoginInfo", adminBeans);
            logger.trace("{} End", ErrorCheckService.getMethodName());
            response.sendRedirect("adminTop");
        }
    }


    public boolean checkInputTextLength(String adminMail, String adminPassword) {
        if (!ErrorCheckService.checkLength(adminMail, /* maxLength= */100, /* minLength= */1)) {
            return false;
        } else if (!ErrorCheckService.checkLength(adminPassword, 128, 1)) {
            return false;
        }
        return true;
    }
}
