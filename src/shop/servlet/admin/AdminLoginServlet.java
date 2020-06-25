package shop.servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.AdminBeans;
import shop.model.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/admin_login.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//DAOのインスタンス作成
		shop.model.service.UserService userService = new UserService();
		String adminMail = request.getParameter("adminMail");
		String adminPassword = request.getParameter("adminPassword");
		AdminBeans adminBeans = userService.fetchAdminLogin(adminMail,adminPassword);

		//推移先
		String path = "";
		HttpSession session = request.getSession();
		if(adminBeans == null) {
			//取得に失敗した場合
			path = "WEB-INF/jsp/admin_login.jsp";
			request.setAttribute("msg","正しい学籍番号かパスワードを入力してください");

		}else {
			//取得した場合
			session.setAttribute("adminLoginInfo", adminBeans);
			path = "WEB-INF/jsp/admin_top.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);

	}

}
