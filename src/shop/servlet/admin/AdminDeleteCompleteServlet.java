package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminDeleteComplete")
public class AdminDeleteCompleteServlet extends HttpServlet {

    UserService    userService    = new UserService();
    ProductService productService = new ProductService();
    private Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session    = request.getSession();
        AdminBeans  adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");
        logger.info("adminLoginInfo={}", adminBeans);

        userService.deleteAdmin(adminBeans);

        productService.deleteProductAll(adminBeans.getAdminMail());

        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_delete_complete.jsp").forward(request, response);
    }
}
