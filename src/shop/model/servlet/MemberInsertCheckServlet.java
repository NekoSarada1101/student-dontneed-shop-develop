package shop.model.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;


@WebServlet("/MemberInsertCheck")
public class MemberInsertCheckServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		MemberBeans memberBeans = new MemberBeans();

		try {
		//会員メールアドレス
		String member_mail = request.getParameter("member_mail");
		//会員氏名
		String member_name = request.getParameter("member_name");
		//会員パスワード
		String member_password = request.getParameter("member_password");
		//クレジットカード
		String credit_card = request.getParameter("credit_card");
		//住所
		String address = request.getParameter("address");
		//郵便番号
		int postal_code = Integer.parseInt(request.getParameter("postal_code"));
		//電話番号
		int tell = Integer.parseInt(request.getParameter("tell"));
		//有効期限
		String deadline = request.getParameter("deadline");
		//セキュリティコード
		int security_code = Integer.parseInt(request.getParameter("security_code"));
		//名義者
		String security_name = request.getParameter("security_name");



		memberBeans.setMemberMail(member_mail);
		memberBeans.setMemberName(member_name);
		memberBeans.setMemberPassword(member_password);
		memberBeans.setCreditCard(credit_card);
		memberBeans.setAddress(address);
		memberBeans.setPostalCode(postal_code);
		memberBeans.setTell(tell);
		memberBeans.setExpirationDate(deadline);
		memberBeans.setSecurityCode(security_code);
		memberBeans.setHolder(security_name);



		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("memberBeans",memberBeans);

		// member_insert_check.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("member_insert_check.jsp");
				dispatch.forward(request, response);
	}


}
