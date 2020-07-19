package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
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
    private Logger logger = LogManager.getLogger();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session    = request.getSession();
        String      memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
        int         productId  = ((ProductBeans) session.getAttribute("productBeans")).getProductId();
        logger.info("productId={}", productId);

        purchaseService.insertCart(memberMail, productId);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/cart_insert_complete.jsp").forward(request, response);
    }
}
