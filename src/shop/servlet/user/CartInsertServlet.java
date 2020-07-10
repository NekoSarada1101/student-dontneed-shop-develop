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

@WebServlet("/cartInsert")
public class CartInsertServlet extends HttpServlet {

    PurchaseService purchaseService = new PurchaseService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
        int productId = ((ProductBeans) session.getAttribute("productBeans")).getProductId();

        purchaseService.insertCart(memberMail, productId);

        request.getRequestDispatcher("WEB-INF/jsp/user/cart_insert_complete.jsp").forward(request, response);
    }
}
