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

@WebServlet("/adminUpdateCheck")
public class AdminUpdateCheckServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session       = request.getSession();
        String      adminMail     = request.getParameter("adminMail");
        String      adminPassword = request.getParameter("adminPassword");
        String      adminName     = request.getParameter("adminName");
        String      postalCode    = request.getParameter("postalCode");
        String      address       = request.getParameter("address");

        if (!checkInputTextLength(adminMail, adminPassword, adminName, postalCode, address)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_input.jsp").forward(request, response);
            return;
        }

        AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");
        adminBeans.setAdminMail(adminMail);
        adminBeans.setAdminPassword(adminPassword);
        adminBeans.setAdminName(adminName);
        adminBeans.setPostalCode(postalCode);
        adminBeans.setAddress(address);

        session.setAttribute("adminBeans", adminBeans);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_check.jsp").forward(request, response);
    }


    public boolean checkInputTextLength(String memberMail, String memberPassword, String memberName, String postalCode, String address) {
        if (!ErrorCheckService.checkLength(memberMail, /* maxLength= */100, /* minLength= */1)) {
            return false;
        } else if (!ErrorCheckService.checkLength(memberPassword, 128, 1)) return false;
        if (!ErrorCheckService.checkLength(memberName, 20, 1)) {
            return false;
        } else if (!ErrorCheckService.checkLength(postalCode, 7, 7)) {
            return false;
        } else if (!ErrorCheckService.checkLength(address, 50, 1)) {
            return false;
        } else if (!ErrorCheckService.checkStringIsNumber(postalCode)) {
            return false;
        }
        return true;
    }
}
