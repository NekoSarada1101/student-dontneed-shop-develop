package shop.servlet.admin;

import shop.model.bean.AdminBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminTop")
public class AdminTopServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminMail = ((AdminBeans) session.getAttribute("adminBeans")).getAdminMail();
        List<ProductBeans> productList = productService.fetchAdminProductList(adminMail);

        session.removeAttribute("productBeans");
        session.removeAttribute("productList");
        session.setAttribute("productList", productList);

        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_top.jsp").forward(request, response);
    }
}
