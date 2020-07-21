package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/genreSearch")
public class GenreSearchServlet extends HttpServlet {

    ProductService productService = new ProductService();
    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession        session     = request.getSession();
        int                genreCode   = Integer.parseInt(request.getParameter("genreCode"));
        List<ProductBeans> productList = productService.fetchSearchProductList(genreCode, "product_id", "desc", "");

        session.setAttribute("productList", productList);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/search_product_list.jsp").forward(request, response);
    }
}
