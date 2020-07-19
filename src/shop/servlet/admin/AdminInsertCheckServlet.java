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

@WebServlet("/adminInsertCheck")
public class AdminInsertCheckServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session       = request.getSession();
        String      adminMail     = request.getParameter("adminMail");
        String      adminPassword = request.getParameter("adminPassword");
        String      adminName     = request.getParameter("adminName");
        String      postalCode    = request.getParameter("postalCode");
        String      address       = request.getParameter("address");

        if (!checkInputText(adminMail, adminPassword, adminName, postalCode, address)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_insert_input.jsp").forward(request, response);
            return;
        }

        AdminBeans adminBeans = new AdminBeans();
        adminBeans.setAdminMail(adminMail);
        adminBeans.setAdminPassword(adminPassword);
        adminBeans.setAdminName(adminName);
        adminBeans.setPostalCode(postalCode);
        adminBeans.setAddress(address);

        session.setAttribute("adminBeans", adminBeans);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_insert_check.jsp").forward(request, response);
    }


    public boolean checkInputText(String adminMail, String adminPassword, String adminName, String postalCode, String address) {
        if (!ErrorCheckService.checkLength(adminMail, 100, 1)) return false;
        if (!ErrorCheckService.checkLength(adminPassword, 128, 1)) return false;
        if (!ErrorCheckService.checkLength(adminName, 20, 1)) return false;
        if (!ErrorCheckService.checkLength(postalCode, 7, 7)) return false;
        if (!ErrorCheckService.checkLength(address, 50, 1)) return false;
        return true;
    }
}
