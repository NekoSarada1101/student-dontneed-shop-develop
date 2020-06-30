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

@WebServlet("/MembeUpdateInputServlet")
public class MemberUpdateInputServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberBeans memberBeans = new MemberBeans();

		memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");

		session.setAttribute("memberBeans",memberBeans);


		// member_update_input.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("member_update_input.jsp");
				dispatch.forward(request, response);
	}

}
