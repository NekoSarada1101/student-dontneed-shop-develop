package shop.servlet.user;

import shop.model.bean.MemberBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberUpdateCheck")
public class MemberUpdateCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String memberMail     = request.getParameter("memberMail");
        String memberPassword = request.getParameter("memberPassword");
        String memberName     = request.getParameter("memberName");
        String postalCode     = request.getParameter("postalCode");
        String address        = request.getParameter("address");
        String tell           = request.getParameter("tell");
        String creditCard     = request.getParameter("creditCard");
        String expirationDate = request.getParameter("expirationDate");
        String holder         = request.getParameter("holder");
        String securityCode   = request.getParameter("securityCode");

        MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
        memberBeans.setMemberMail(memberMail);
        memberBeans.setMemberPassword(memberPassword);
        memberBeans.setMemberName(memberName);
        memberBeans.setPostalCode(postalCode);
        memberBeans.setAddress(address);
        memberBeans.setTell(tell);
        memberBeans.setCreditCard(creditCard);
        memberBeans.setExpirationDate(expirationDate);
        memberBeans.setHolder(holder);
        memberBeans.setSecurityCode(securityCode);

        session.setAttribute("memberBeans", memberBeans);

        request.getRequestDispatcher("WEB-INF/jsp/user/member_update_check.jsp").forward(request, response);
    }
}
