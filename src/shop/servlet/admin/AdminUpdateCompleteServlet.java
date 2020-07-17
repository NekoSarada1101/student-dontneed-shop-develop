package shop.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.AdminBeans;


@WebServlet("/AdminUpdateComplete")
public class AdminUpdateCompleteServlet extends HttpServlet {

	AdminService adminService = new AdminService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session		= request.getSession();
		AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");

		if(!adminBeans.getAdminMail().equals(((AdminBeans) session.getAttribute("adminLoginInfo")).getAdminMail()))
            if (adminService.checkAdminMailExists(adminBeans.getAdminMail())) {
                String errorMessage = "そのメールアドレスは既に登録済みです";
                request.setAttribute("errorMessage", errorMessage);

                request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_input.jsp").forward(request, response);
                return;
            }

		adminService.updateAdmin(adminBeans);
		session.setAttribute("adminLoginIngo", adminBeans);
		session.removeAttribute("adminBeans");
		request.getRequestDispatcher("WEB-INF/jsp/admin/admin_update_complete.jsp").forward(request, response);
}
}
