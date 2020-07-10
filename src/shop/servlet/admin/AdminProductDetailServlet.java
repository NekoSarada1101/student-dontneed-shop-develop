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

@WebServlet("/adminProductDetail")
public class AdminProductDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int index = 0;
        try { //商品一覧から遷移したら
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) { //商品情報変更入力画面から遷移したら
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

        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_product_detail.jsp").forward(request, response);
    }
}
