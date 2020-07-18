package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.CommonService;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminTop")
public class AdminTopServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private Logger         logger         = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session = request.getSession();
        String adminMail = ((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail();
        List<ProductBeans> productList = productService.fetchAdminProductList(adminMail);
        logger.info("adminMail={}", adminMail);
        logger.info("productList={}", productList);

        session.removeAttribute("productBeans");
        session.removeAttribute("productList");
        session.setAttribute("productList", productList);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_top.jsp").forward(request, response);
    }
}
