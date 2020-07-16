
package shop.servlet.admin;

import shop.model.bean.AdminBeans;
import shop.model.bean.ProductBeans;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }
        System.out.println(filePart);

        if (!checkInputText(productName, price, productExplanation)) {
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
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

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_check.jsp").forward(request, response);
    }

    public boolean checkInputText(String productName, int price, String productExplanation) {
        if (!productService.checkLength(productName, 30, 1)) return false;
        if (!productService.checkLength(productExplanation, 400, 1)) return false;
        return true;
    }
}
