package shop.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;
import shop.model.service.UserService;

@WebServlet("/memberDeleteComplete")
public class MemberDeleteCompleteServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");

        userService.deleteMember(memberBeans);

        request.getRequestDispatcher("WEB-INF/jsp/admin/member_delete_complete.jsp").forward(request, response);
    }
}
