package shop.servlet.admin;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/productUpdateCheck")
@MultipartConfig(location = "/tmp")
public class ProductUpdateCheckServlet extends HttpServlet {

    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String      productName        = request.getParameter("productName");
        int         price              = Integer.parseInt(request.getParameter("price"));
        Part        filePart           = request.getPart("image");
        InputStream inputStream        = filePart.getInputStream();
        String      productExplanation = request.getParameter("productExplanation");
        int         genreCode          = Integer.parseInt(request.getParameter("genre"));

        HttpSession session = request.getSession();

        ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");
        productBeans.setProductName(productName);
        productBeans.setPrice(price);
        if (inputStream.available() == 0) {
            //画像が選択されてない時
            //登録済みの画像を使用する
            productBeans.setImage(productBeans.getImage());
        } else {
            //画像が選択されている時
            //選択された画像を使用する
            productBeans.setImage(productService.convertInputStreamToByteArray(inputStream));
        }
        productBeans.setProductExplanation(productExplanation);
        productBeans.setGenreCode(genreCode);

        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_update_check.jsp").forward(request, response);
    }
}
