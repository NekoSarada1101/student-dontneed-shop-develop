
package shop.servlet.user;

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
import java.util.List;

@WebServlet("/productSearchAndDisplay")
public class ProductSearchAndDisplayServlet extends HttpServlet {

    ProductService productService = new ProductService();
    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session = request.getSession();

        int genreCode = Integer.parseInt(request.getParameter("genreCode"));
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        String searchWord = request.getParameter("searchWord");
        logger.info("genreCode={}", genreCode);
        logger.info("sortColumn={}", sortColumn);
        logger.info("sortOrder={}", sortOrder);
        logger.info("searchWord={}", searchWord);

        List<ProductBeans> productList = productService.fetchSearchProductList(genreCode, sortColumn, sortOrder, searchWord);
        logger.info("productList={}", productList);
        session.setAttribute("productList", productList);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/search_product_list.jsp").forward(request, response);
    }
}
