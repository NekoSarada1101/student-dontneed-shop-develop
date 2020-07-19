package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.service.CommonService;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminInsertComplete")
public class AdminInsertCompleteServlet extends HttpServlet {

    UserService userService = new UserService();
    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session = request.getSession();

        AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");
        logger.info("adminBeans={}", adminBeans);

        if (userService.checkAdminMailExists(adminBeans.getAdminMail())) {
            String errorMessage = "そのメールアドレスは既に登録済みです";
            request.setAttribute("errorMessage", errorMessage);
            logger.trace("{} End", CommonService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_insert_input.jsp").forward(request, response);
            return;
        }

        userService.insertAdmin(adminBeans);
        session.removeAttribute("adminBeans");
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_insert_complete.jsp").forward(request, response);
    }
}
