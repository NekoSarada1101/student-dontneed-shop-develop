
package shop.servlet.user;

import shop.model.bean.MemberBeans;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberMail     = request.getParameter("memberMail");
        String memberPassword = request.getParameter("memberPassword");

        MemberBeans memberBeans = userService.fetchMemberLogin(memberMail, memberPassword);

        //遷移先
        HttpSession session = request.getSession();
        if (memberBeans == null) { //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
        } else {
            //取得した場合
            session.setAttribute("memberLoginInfo", memberBeans);
            response.sendRedirect("memberTop");
        }
    }
}
