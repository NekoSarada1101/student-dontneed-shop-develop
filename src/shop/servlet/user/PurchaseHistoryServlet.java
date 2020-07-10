
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/purchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet {

    private PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Map<String, Object>> purchaseHistory = purchaseService.fetchPurchaseHistory(((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail());

        List<ProductBeans> productList = new ArrayList<>();
        for (Map<String, Object> purchaseMap : purchaseHistory) {
            productList.add((ProductBeans) purchaseMap.get("productBeans"));
        }
        session.setAttribute("productList", productList);

        request.getRequestDispatcher("WEB-INF/jsp/user/purchase_history.jsp").forward(request, response);
    }
}
