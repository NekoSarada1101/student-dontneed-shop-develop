package shop.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.AdminBeans;

/**
 * Servlet implementation class AdminUpdateInputServlet
 */
@WebServlet("/AdminUpdateInput")
public class AdminUpdateInputServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session			= request.getSession();
		AdminBeans adminBeans		= new AdminBeans();
		AdminBeans adminLoginInfo	= (AdminBeans) session.getAttribute("adminLoginInfo");

		adminBeans.setAdminMail(adminLoginInfo.getAdminMail());
		adminBeans.setAdminPassword(adminLoginInfo.getAdminPassword());
		adminBeans.setAdminName(adminLoginInfo.getAdminName());
		adminBeans.setPostalCode(adminLoginInfo.getPostalCode());
		adminBeans.setAddress(adminLoginInfo.getAddress());

		session.setAttribute("adminBeans", adminBeans);

		request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_input.jsp").forward(request, response);
	}

}
