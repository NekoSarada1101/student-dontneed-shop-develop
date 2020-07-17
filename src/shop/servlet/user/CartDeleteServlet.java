package shop.servlet.user;

import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.PurchaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cartDelete")
public class CartDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession        session  = request.getSession();
        List<ProductBeans> cartList = (List<ProductBeans>) session.getAttribute("productList");

        String       memberMail   = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
        int          index        = Integer.parseInt(request.getParameter("index"));
        ProductBeans productBeans = cartList.get(index);
        int          productId    = productBeans.getProductId();

        PurchaseService purchaseService = new PurchaseService();
        boolean      couldDelete  = purchaseService.deleteCart(memberMail, productId);
        response.sendRedirect("cartDisplay");
    }
}
