package shop.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.AdminBeans;
import shop.model.service.UserService;

@WebServlet("/adminInsertComplete")
public class AdminInsertCompleteServlet extends HttpServlet {
	UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminBeans");

        if (userService.checkAdminMailExists(adminBeans.getAdminMail())) {
            String errorMessage = "そのメールアドレスは既に登録済みです";
            request.setAttribute("errorMessage", errorMessage);

            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_insert_input.jsp").forward(request, response);
            return;
        }

        userService.insertAdmin(adminBeans);
        session.removeAttribute("adminBeans");

        request.getRequestDispatcher("WEB-INF/jsp/user/admin_insert_complete.jsp").forward(request, response);
    }
}
