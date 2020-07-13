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

@WebServlet("/memberUpdateComplete")
public class MemberUpdateCompleteServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session     = request.getSession();
        MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");

        if (!memberBeans.getMemberMail().equals(((MemberBeans) session.getAttribute("memberLoginInfo")).getMemberMail()))
            if (userService.checkMemberMailExists(memberBeans.getMemberMail())) {
                String errorMessage = "そのメールアドレスは既に登録済みです";
                request.setAttribute("errorMessage", errorMessage);

                request.getRequestDispatcher("WEB-INF/jsp/user/member_update_input.jsp").forward(request, response);
            }

        userService.updateMember(memberBeans);
        session.setAttribute("memberLoginInfo", memberBeans);
        session.removeAttribute("memberBeans");

        request.getRequestDispatcher("WEB-INF/jsp/user/member_update_complete.jsp").forward(request, response);
    }
}

