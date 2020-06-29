package shop.servlet.admin;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/productInsertComplete")
public class ProductInsertCompleteServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductBeans productBeans = (ProductBeans) session.getAttribute("productBeans");

        productService.insertProduct(productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_insert_complete.jsp").forward(request, response);
    }
}
