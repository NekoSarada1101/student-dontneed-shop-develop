
package shop.servlet.admin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

@WebServlet("/productInsertCheck")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)
public class ProductInsertCheckServlet extends HttpServlet {

    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        Part filePart = request.getPart("image");
        InputStream inputStream = filePart.getInputStream();
        String productExplanation = request.getParameter("productExplanation");
        int genreCode = Integer.parseInt(request.getParameter("genre"));

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductName(productName);
        productBeans.setPrice(price);
        productBeans.setImage(productService.convertInputStreamToByteArray(inputStream));
        productBeans.setProductExplanation(productExplanation);
        productBeans.setGenreCode(genreCode);

        HttpSession session = request.getSession();
        //        productBeans.setAdminMail(((AdminBeans) session.getAttribute("adminBeans")).getAdminMail());
        productBeans.setAdminMail("ryouta@gmail.com");

        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_check.jsp").forward(request, response);
    }
}
