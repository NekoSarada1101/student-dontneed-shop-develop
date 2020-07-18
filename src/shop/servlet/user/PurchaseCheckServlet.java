
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
import java.util.Map;

@WebServlet("/purchaseCheck")
public class PurchaseCheckServlet extends HttpServlet {

    private PurchaseService purchaseService = new PurchaseService();
    private Logger          logger          = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        HttpSession session = request.getSession();

        String memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
        Map<String, List<ProductBeans>> purchaseMap = purchaseService.checkExistsStock(memberMail);
        logger.info("purchaseMap={}", purchaseMap);


        String errorMessage = "";

        if (purchaseMap.get("deleteList").size() != 0) {
            List<ProductBeans> deleteList = purchaseMap.get("deleteList");
            for (ProductBeans productBeans : deleteList) {
                purchaseService.deleteCart(memberMail, productBeans.getProductId());
                errorMessage += productBeans.getProductName() + "\n";
            }
            errorMessage += "が既に販売済みであったためカートから削除しました";
        }

        request.setAttribute("errorMessage", errorMessage);
        session.setAttribute("productList", purchaseMap.get("purchaseList"));
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/purchase_check.jsp").forward(request, response);
    }
}
