package shop.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.PurchaseService;

@WebServlet("/cartDelete")
public class CartDeleteServle extends HttpServlet {

    PurchaseService purchaseService = new PurchaseService();
    ProductBeans productBeans = new ProductBeans();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<ProductBeans> cartList = new ArrayList<ProductBeans>();
    	HttpSession session = request.getSession();
    	cartList = (List<ProductBeans>)session.getAttribute("cartList");
    	String memberMail = ((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail();
    	int index = Integer.parseInt( request.getParameter("index"));
    	productBeans = cartList.get(index);
    	int productId = productBeans.getProductId();
    	boolean couldDelete = purchaseService.deleteCart(memberMail, productId);
    	request.setAttribute("couldDelete", couldDelete);
		request.getRequestDispatcher("WEB-INF/jsp/user/cart_display.jsp").forward(request, response);
    }

}
