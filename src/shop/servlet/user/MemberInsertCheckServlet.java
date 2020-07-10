package shop.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;


@WebServlet("/memberInsertCheck")
public class MemberInsertCheckServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		MemberBeans memberBeans = new MemberBeans();


		//会員メールアドレス
		String memberMail = request.getParameter("memberMail");
		//会員氏名
		String memberName = request.getParameter("memberName");
		//会員パスワード
		String memberPassword = request.getParameter("memberPassword");
		//クレジットカード
		int creditCard = Integer.parseInt(request.getParameter("creditCard"));
		//住所
		String address = request.getParameter("address");
		//郵便番号
		int postalCode = Integer.parseInt(request.getParameter("postalCode"));
		//電話番号
		int tell = Integer.parseInt(request.getParameter("tell"));
		//有効期限
		String deadline = request.getParameter("deadline");
		//セキュリティコード
		int securityCode = Integer.parseInt(request.getParameter("securityCode"));
		//名義者
		String securityName = request.getParameter("securityName");



		memberBeans.setMemberMail(memberMail);
		memberBeans.setMemberName(memberName);
		memberBeans.setMemberPassword(memberPassword);
		memberBeans.setCreditCard(creditCard);
		memberBeans.setAddress(address);
		memberBeans.setPostalCode(postalCode);
		memberBeans.setTell(tell);
		memberBeans.setExpirationDate(deadline);
		memberBeans.setSecurityCode(securityCode);
		memberBeans.setHolder(securityName);





		session.setAttribute("memberBeans",memberBeans);

		// member_insert_check.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_check.jsp");
				dispatch.forward(request, response);
	}


}

