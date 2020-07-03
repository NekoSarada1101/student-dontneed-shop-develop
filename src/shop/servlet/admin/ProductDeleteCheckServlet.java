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

        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        }catch (NumberFormatException e){
            index = (int) session.getAttribute("index");
        }

    	List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductId(productList.get(index).getProductId());
        productBeans.setProductName(productList.get(index).getProductName());
        productBeans.setPrice(productList.get(index).getPrice());
        productBeans.setImage(productList.get(index).getImage());
        productBeans.setProductExplanation(productList.get(index).getProductExplanation());
        productBeans.setIsSold(productList.get(index).getIsSold());
        productBeans.setGenreCode(productList.get(index).getGenreCode());

        session.setAttribute("index", index);
        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_delete_check.jsp").forward(request, response);
    }
}