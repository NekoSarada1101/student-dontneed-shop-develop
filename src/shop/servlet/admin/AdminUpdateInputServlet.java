package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.service.ErrorCheckService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminUpdateInput")
public class AdminUpdateInputServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session        = request.getSession();
        AdminBeans  adminBeans     = new AdminBeans();
        AdminBeans  adminLoginInfo = (AdminBeans) session.getAttribute("adminLoginInfo");
        logger.info("adminLoginInfo={}", adminLoginInfo);

        adminBeans.setAdminMail(adminLoginInfo.getAdminMail());
        adminBeans.setAdminPassword(adminLoginInfo.getAdminPassword());
        adminBeans.setAdminName(adminLoginInfo.getAdminName());
        adminBeans.setPostalCode(adminLoginInfo.getPostalCode());
        adminBeans.setAddress(adminLoginInfo.getAddress());

        session.setAttribute("adminBeans", adminBeans);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_input.jsp").forward(request, response);
    }
}
