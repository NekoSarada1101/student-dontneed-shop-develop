package shop.model.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;

@WebServlet("/MembeUpdateCheckServlet")
public class MemberUpdateCheckServlet extends HttpServlet {


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			MemberBeans memberBeans = new MemberBeans();


			//会員メールアドレス
			String member_mail = request.getParameter("memberMail");
			//会員氏名
			String member_name = request.getParameter("memberName");
			//会員パスワード
			String member_password = request.getParameter("memberPassword");
			//住所
			String address = request.getParameter("address");
			//郵便番号
			int postal_code = Integer.parseInt(request.getParameter("postalCode"));
			//電話番号
			int tell = Integer.parseInt(request.getParameter("tell"));
			//クレジットコード
			int creditCard = Integer.parseInt(request.getParameter("creditCard"));
			//有効期限
			String deadline = request.getParameter("expirationDate");
			//セキュリティコード
			int security_code = Integer.parseInt(request.getParameter("securityCard"));
			//名義者
			String security_name = request.getParameter("holder");



			memberBeans.setMemberMail(member_mail);
			memberBeans.setMemberName(member_name);
			memberBeans.setMemberPassword(member_password);
			memberBeans.setAddress(address);
			memberBeans.setPostalCode(postal_code);
			memberBeans.setTell(tell);
			memberBeans.setExpirationDate(deadline);
			memberBeans.setCreditCard(creditCard);
			memberBeans.setSecurityCard(security_code);
			memberBeans.setHolder(security_name);





			session.setAttribute("memberBeans",memberBeans);

			// member_insert_check.jsp にページ遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("member_insert_check.jsp");
					dispatch.forward(request, response);
		}
}
