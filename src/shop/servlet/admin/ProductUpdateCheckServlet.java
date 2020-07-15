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
        Part        filePart           = request.getPart("image");
        InputStream inputStream        = filePart.getInputStream();
        String      productExplanation = request.getParameter("productExplanation");
        int         price;
        int         genreCode;
        try {
            price     = Integer.parseInt(request.getParameter("price"));
            genreCode = Integer.parseInt(request.getParameter("genre"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }

        if (!checkInputText(productName, price, productExplanation)) {
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }

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

    public boolean checkInputText(String productName, int price, String productExplanation) {
        if (!productService.checkLength(productName, 30, 1)) return false;
        if (!productService.checkLength(productExplanation, 400, 1)) return false;
        return true;
    }
}
