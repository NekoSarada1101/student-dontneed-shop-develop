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

@WebServlet("/adminUpdateComplete")
public class AdminUpdateCompleteServlet extends HttpServlet {

    UserService userService = new UserService();
    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session        = request.getSession();
        AdminBeans  adminLoginInfo = (AdminBeans) session.getAttribute("adminBeans");
        logger.info("adminBeans={}", adminLoginInfo);

        //メールアドレスが変更された時
        if (!adminLoginInfo.getAdminMail().equals(((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail())) {
            if (userService.checkAdminMailExists(adminLoginInfo.getAdminMail())) {
                String errorMessage = "そのメールアドレスは既に登録済みです";
                request.setAttribute("errorMessage", errorMessage);
                logger.trace("{} End", ErrorCheckService.getMethodName());
                request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_input.jsp").forward(request, response);
                return;
            }
        }

        userService.updateAdmin(adminLoginInfo);

        session.setAttribute("adminLoginInfo", adminLoginInfo);
        session.removeAttribute("adminBeans");
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_complete.jsp").forward(request, response);
    }
}

