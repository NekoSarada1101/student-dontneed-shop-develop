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
        int index = Integer.parseInt(request.getParameter("index"));

        HttpSession session = request.getSession();
        List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

        ProductBeans productBeans = productList.get(index);
        session.setAttribute("productBeans", productBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_product_detail.jsp").forward(request, response);
    }
}
