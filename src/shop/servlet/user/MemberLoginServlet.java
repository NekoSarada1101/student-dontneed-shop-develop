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

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/MemberLogin")

public class MemberLoginServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public MemberLoginServlet() {
     super();
     // TODO Auto-generated constructor stub
 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp");
		dispatcher .forward(request, response);
	}
/**
 * @see HttpServlet#doPost(HttpServletRequest request,HttpServletResponse response
 */
	@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

 UserService userService = new UserService();
 MemberBeans memberBeans = new MemberBeans();

 String memberMail = request.getParameter("memberMail");
 String memberPassword = request.getParameter("memberPassword");
 memberBeans = userService.fetchMemberLogin(memberMail, memberPassword);

 String path ="";
 HttpSession session = request.getSession();

 if(memberBeans == null) {
	 String errorMessage = "ログインに失敗しました";
		session.setAttribute("errorMessage", errorMessage);
		path = "WEB-INF/jsp/user/member_login.jsp";
	}else {
		session.setAttribute("logininfo", memberBeans);
		path = "WEB-INF/jsp/user/member_top.jsp";
	}

		request.getRequestDispatcher(path).forward(request,response);
}

}