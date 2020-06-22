package shop.servlet.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.AdminBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

@WebServlet("/adminTop")
public class AdminTopServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //String adminMail = ((AdminBeans) session.getAttribute("admin")).getAdminMail();
        List<ProductBeans> productList = productService.fetchAdminProductList("ryouta@gmail.com");

        List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

        //test//
        AdminBeans adminBeans = new AdminBeans();
        adminBeans.setAdminName("原田");
        session.setAttribute("admin", adminBeans);
        //test//

        session.setAttribute("productList", productList);

        session.setAttribute("genreInfoList", genreInfoList);

        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_top.jsp").forward(request, response);
    }
}
