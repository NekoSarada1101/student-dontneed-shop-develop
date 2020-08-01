package shop.servlet.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;

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

    private Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session = request.getSession();

        int    index = 0;
        String from = request.getParameter("from");
        if (from.equals("adminTop") || from.equals("search")) {
            //管理者トップから遷移したら
            index = Integer.parseInt(request.getParameter("index"));
        } else if (from.equals("/productUpdateInput")) {
            //商品情報変更入力画面から遷移したら
            index = (int) session.getAttribute("index");
        }
        logger.info("index={}", index);

        List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");
        logger.info("productList.size={}", productList.size());

        ProductBeans productBeans = new ProductBeans();
        productBeans.setProductId(productList.get(index).getProductId());
        productBeans.setProductName(productList.get(index).getProductName());
        productBeans.setPrice(productList.get(index).getPrice());
        productBeans.setImage(productList.get(index).getImage());
        productBeans.setProductExplanation(productList.get(index).getProductExplanation());
        productBeans.setSold(productList.get(index).isSold());
        productBeans.setGenreCode(productList.get(index).getGenreCode());

        session.setAttribute("index", index);
        session.setAttribute("productBeans", productBeans);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_product_detail.jsp").forward(request, response);
    }
}
