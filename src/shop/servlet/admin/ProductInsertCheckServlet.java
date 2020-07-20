
package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/productInsertCheck")
@MultipartConfig(location = "/tmp")
public class ProductInsertCheckServlet extends HttpServlet {

    ProductService productService = new ProductService();
    private Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        String      productName        = request.getParameter("productName");
        Part        filePart           = request.getPart("image");
        InputStream inputStream        = filePart.getInputStream();
        String      productExplanation = request.getParameter("productExplanation");
        int         price;
        int         genreCode;
        try {
            price = Integer.parseInt(request.getParameter("price"));
            genreCode = Integer.parseInt(request.getParameter("genre"));
        } catch (NumberFormatException e) {
            //価格とジャンルコードに数字以外が入力されたら
            e.printStackTrace();
            logger.error("error={}", e);
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_input.jsp").forward(request, response);
            return;
        }

        logger.info("productName={}", productName);
        logger.info("inputStream={}", inputStream);
        logger.info("productExplanation={}", productExplanation);
        logger.info("price={}", price);
        logger.info("genreCode={}", genreCode);

        if (!checkInputTextLength(productName, price, productExplanation)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_input.jsp").forward(request, response);
            return;
        }

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductName(productName);
        productBeans.setPrice(price);
        productBeans.setImage(productService.convertInputStreamToByteArray(inputStream));
        productBeans.setProductExplanation(productExplanation);
        productBeans.setGenreCode(genreCode);

        HttpSession session = request.getSession();
        productBeans.setAdminMail(((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail());

        session.setAttribute("productBeans", productBeans);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_check.jsp").forward(request, response);
    }


    public boolean checkInputTextLength(String productName, int price, String productExplanation) {
        if (!ErrorCheckService.checkLength(productName, /* maxLength= */30, /* minLength= */1)) {
            return false;
        } else if (!ErrorCheckService.checkLength(productExplanation, 400, 1)) {
            return false;
        }
        return true;
    }
}
