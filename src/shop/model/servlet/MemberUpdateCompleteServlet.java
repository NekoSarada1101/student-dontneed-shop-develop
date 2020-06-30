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
import shop.model.service.UserService;

@WebServlet("/MembeUpdateCompleteServlet")
public class MemberUpdateCompleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		MemberBeans memberBeans = new MemberBeans();

		memberBeans = (MemberBeans) session.getAttribute("memberBeans");
		memberLoginInfo = (MemberLoginInfo) session.getAttribute("memberLoginInfo");

		String errorMessage = "";

		UserService userService = new UserService();

		//ログインメールアドレスと更新メールアドレスの比較
		if(memberBeans.getMemberMail() ==  memberLoginInfo.getMemberMail()) {

			//更新処理
			boolean couldUpdate = userService.updateMember(memberBeans);

			if(couldUpdate == true) {

				session.setAttribute("MemberLoginInfo",memberBeans);

				session.removeAttribute("mamberBeans");

				// member_insert_complete.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("member_update_complete.jsp");
				dispatch.forward(request, response);

			}else {

				errorMessage = "会員情報更新に失敗しました";

				session.setAttribute(errorMessage, "errorMessage");

				// member_insert_input.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("member_update_input.jsp");
				dispatch.forward(request, response);


			}



			}else {

				//新規メールアドレスがすでに存在するか確認
				boolean exists = userService.checkMemberMailExists( memberBeans.getMemberMail() );

				if(exists == true) {

					errorMessage = "すでに新しいメールアドレスは存在します";

					session.setAttribute(errorMessage, "errorMessage");

					// member_insert_input.jsp にページ遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("member_update_input.jsp");
					dispatch.forward(request, response);


				}else {

					//更新処理
					boolean couldUpdate = userService.updateMember(memberBeans);

					if(couldUpdate == true) {

						session.removeAttribute("mamberBeans");

						// member_insert_complete.jsp にページ遷移
						RequestDispatcher dispatch = request.getRequestDispatcher("member_update_complete.jsp");
						dispatch.forward(request, response);

					}else {

						errorMessage = "会員情報更新に失敗しました";

						session.setAttribute(errorMessage, "errorMessage");

						// member_insert_input.jsp にページ遷移
						RequestDispatcher dispatch = request.getRequestDispatcher("member_update_input.jsp");
						dispatch.forward(request, response);


					}

				}
			}
	}

}
