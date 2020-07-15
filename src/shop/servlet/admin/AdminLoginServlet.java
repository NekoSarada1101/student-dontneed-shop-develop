
package shop.servlet.admin;

import shop.model.bean.AdminBeans;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminMail = request.getParameter("adminMail");
        String adminPassword = request.getParameter("adminPassword");

        if (!checkInputText(adminMail, adminPassword)) {
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }

        AdminBeans adminBeans = userService.fetchAdminLogin(adminMail, adminPassword);

        //遷移先
        HttpSession session = request.getSession();
        if (adminBeans == null) { //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
        } else {
            //取得した場合
            session.setAttribute("adminLoginInfo", adminBeans);
            response.sendRedirect("adminTop");
        }
    }

    public boolean checkInputText(String adminMail, String adminPassword) {
        if (!userService.checkLength(adminMail, 100, 1)) return false;
        if (!userService.checkLength(adminPassword, 128, 1)) return false;
        return true;
    }
}
