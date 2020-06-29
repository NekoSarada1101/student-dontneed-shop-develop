
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

        AdminBeans adminBeans = userService.fetchAdminLogin(adminMail, adminPassword);

        //遷移先
        String path = "";
        HttpSession session = request.getSession();
        if (adminBeans == null) {
            //取得に失敗した場合
            path = "WEB-INF/jsp/admin/admin_login.jsp";
            request.setAttribute("error_message", "正しい学籍番号とパスワードを入力してください");
            response.sendRedirect("adminLogin");

        } else {
            //取得した場合
            session.setAttribute("adminBeans", adminBeans);
            response.sendRedirect("adminTop");
        }
    }
}
