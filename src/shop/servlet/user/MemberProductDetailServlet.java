package shop.servlet.user;

import shop.model.bean.ProductBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/memberProductDetail")
public class MemberProductDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int index = Integer.parseInt(request.getParameter("index"));

        List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductId(productList.get(index).getProductId());
        productBeans.setProductName(productList.get(index).getProductName());
        productBeans.setPrice(productList.get(index).getPrice());
        productBeans.setImage(productList.get(index).getImage());
        productBeans.setProductExplanation(productList.get(index).getProductExplanation());
        productBeans.setIsSold(productList.get(index).getIsSold());
        productBeans.setGenreCode(productList.get(index).getGenreCode());

        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/user/member_product_detail.jsp").forward(request, response);
    }
}
