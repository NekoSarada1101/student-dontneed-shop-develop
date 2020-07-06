package shop.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

@WebServlet("/productDeleteCheck")
public class ProductDeleteCheckServlet extends HttpServlet {

    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

    	List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
    	int productListIndex = (int) session.getAttribute("productListIndex");

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductId(productList.get(productListIndex).getProductId());
        productBeans.setProductName(productList.get(productListIndex).getProductName());
        productBeans.setPrice(productList.get(productListIndex).getPrice());
        productBeans.setImage(productList.get(productListIndex).getImage());
        productBeans.setProductExplanation(productList.get(productListIndex).getProductExplanation());
        productBeans.setIsSold(productList.get(productListIndex).getIsSold());
        productBeans.setGenreCode(productList.get(productListIndex).getGenreCode());

        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_delete_check.jsp").forward(request, response);
    }
}