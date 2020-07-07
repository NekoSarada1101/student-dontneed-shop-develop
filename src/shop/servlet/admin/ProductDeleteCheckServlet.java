package shop.servlet.admin;

import shop.model.bean.ProductBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDeleteCheck")
public class ProductDeleteCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
        int index = Integer.parseInt(request.getParameter("index"));

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductId(productList.get(index).getProductId());
        productBeans.setProductName(productList.get(index).getProductName());
        productBeans.setPrice(productList.get(index).getPrice());
        productBeans.setImage(productList.get(index).getImage());
        productBeans.setProductExplanation(productList.get(index).getProductExplanation());
        productBeans.setIsSold(productList.get(index).getIsSold());
        productBeans.setGenreCode(productList.get(index).getGenreCode());

        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/product_delete_check.jsp").forward(request, response);
    }
}
