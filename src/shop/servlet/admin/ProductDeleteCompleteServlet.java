package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet("/productDeleteComplete")
public class ProductDeleteCompleteServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private Logger         logger         = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession  session      = request.getSession();
        ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
        logger.info("productBeans={}", productBeans);

        productService.deleteProduct(productBeans);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/product_delete_complete.jsp").forward(request, response);
    }
}
