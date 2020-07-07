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

@WebServlet("/cartDisplay")
public class CartDisplayServlet extends HttpServlet {

    PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();

        List<ProductBeans> cartList = purchaseService.fetchCartList(memberMail);

        session.setAttribute("cartList", cartList);
        request.getRequestDispatcher("WEB-INF/jsp/user/cart_display.jsp").forward(request, response);
    }
}
