
package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
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

@WebServlet("/adminProductSearchAndDisplay")
public class AdminProductSearchAndDisplayServlet extends HttpServlet {

    ProductService productService = new ProductService();
    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        HttpSession session = request.getSession();

        String adminMail  = ((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail();
        int    genreCode  = Integer.parseInt(request.getParameter("genreCode"));
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder  = request.getParameter("sortOrder");
        String searchWord = request.getParameter("searchWord");
        int    page       = Integer.parseInt(request.getParameter("page"));
        logger.info("genreCode={}", genreCode);
        logger.info("sortColumn={}", sortColumn);
        logger.info("sortOrder={}", sortOrder);
        logger.info("searchWord={}", searchWord);
        logger.info("page={}", page);


        if (!ErrorCheckService.checkAllowedSortColumn(sortColumn) || !ErrorCheckService.checkAllowedSortOrder(sortOrder)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/search_product_list.jsp").forward(request, response);
            return;
        }

        List<ProductBeans> productList = productService.fetchAdminSearchProductList(adminMail, genreCode, sortColumn, sortOrder, searchWord);
        logger.info("productList.size={}", productList.size());

        session.setAttribute("productList", productList);
        request.setAttribute("page", page);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/search_product_list.jsp").forward(request, response);
    }
}
