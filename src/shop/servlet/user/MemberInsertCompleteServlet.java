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
import shop.model.service.UserService;


@WebServlet("/memberInsertComplete")
public class MemberInsertCompleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService userService = new UserService();

		HttpSession session = request.getSession();

		MemberBeans memberBeans = new MemberBeans();

		memberBeans = (MemberBeans) session.getAttribute("memberBeans");

		String errorMessage = "";

		boolean exists = userService.checkMemberMailExists( memberBeans.getMemberMail() );

		if(exists == true) {

			errorMessage = "すでに会員情報が存在します";

			session.setAttribute(errorMessage, "errorMessage");

			// member_insert_input.jsp にページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_input.jsp");
			dispatch.forward(request, response);


		}else {

			boolean couldInsert = userService.insertMember(memberBeans);

			if(couldInsert == true) {

				session.removeAttribute("mamberBeans");

				// member_insert_complete.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_complete.jsp");
				dispatch.forward(request, response);

			}else {

				errorMessage = "会員情報登録に失敗しました";

				session.setAttribute(errorMessage, "errorMessage");

				// member_insert_input.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_input.jsp");
				dispatch.forward(request, response);


			}

		}






	}




}
