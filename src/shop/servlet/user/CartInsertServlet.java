package shop.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.PurchaseService;

public class CartInsertServlet extends HttpServlet {

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


		HttpSession session = request.getSession();
		ProductBeans productBeans = (ProductBeans)session.getAttribute("productBeans");
		MemberBeans memberBeans = (MemberBeans)session.getAttribute("memberBeans");

		int productId = productBeans.getProductId();
		String memberMail = memberBeans.getMemberMail();

		PurchaseService purchaseService = new PurchaseService();
		boolean couldInsert = purchaseService.insertCart(memberMail, productId);



		request.getRequestDispatcher("WEB-INF/jsp/user/cart_insert_complete.jsp").forward(request, response);
	}
}
