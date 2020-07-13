package shop.servlet.user;

import shop.model.bean.MemberBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberUpdateInput")
public class MemberUpdateInputServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session         = request.getSession();
        MemberBeans memberBeans     = new MemberBeans();
        MemberBeans memberLoginInfo = (MemberBeans) session.getAttribute("memberLoginInfo");

        memberBeans.setMemberMail(memberLoginInfo.getMemberMail());
        memberBeans.setMemberPassword(memberLoginInfo.getMemberPassword());
        memberBeans.setMemberName(memberLoginInfo.getMemberName());
        memberBeans.setPostalCode(memberLoginInfo.getPostalCode());
        memberBeans.setAddress(memberLoginInfo.getAddress());
        memberBeans.setTell(memberLoginInfo.getTell());
        memberBeans.setCreditCard(memberLoginInfo.getCreditCard());
        memberBeans.setExpirationDate(memberLoginInfo.getExpirationDate());
        memberBeans.setHolder(memberLoginInfo.getHolder());
        memberBeans.setSecurityCode(memberLoginInfo.getSecurityCode());

        session.setAttribute("memberBeans", memberBeans);

        request.getRequestDispatcher("WEB-INF/jsp/user/member_update_input.jsp").forward(request, response);
    }
}
