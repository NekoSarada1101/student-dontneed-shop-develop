
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/purchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet {

    private PurchaseService purchaseService = new PurchaseService();
    private Logger          logger          = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session = request.getSession();

        List<Map<String, Object>> purchaseHistory = purchaseService.fetchPurchaseHistory(((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail());
        logger.info("purchaseHistory={}", purchaseHistory);

        List<ProductBeans> productList = new ArrayList<>();
        for (Map<String, Object> purchaseMap : purchaseHistory) {
            productList.add((ProductBeans) purchaseMap.get("productBeans"));
        }

        session.setAttribute("productList", productList);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/purchase_history.jsp").forward(request, response);
    }
}
