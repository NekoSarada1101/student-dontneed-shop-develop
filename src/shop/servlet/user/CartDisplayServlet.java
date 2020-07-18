package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.CommonService;
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
    private Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session    = request.getSession();
        String      memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
        logger.info("memberMail={}", memberMail);

        List<ProductBeans> cartList = purchaseService.fetchCartList(memberMail);
        logger.info("cartList={}", cartList);

        session.setAttribute("productList", cartList);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/cart_display.jsp").forward(request, response);
    }
}
