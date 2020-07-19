package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.service.ErrorCheckService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberInsertCheck")
public class MemberInsertCheckServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session        = request.getSession();
        String      memberMail     = request.getParameter("memberMail");
        String      memberPassword = request.getParameter("memberPassword");
        String      memberName     = request.getParameter("memberName");
        String      postalCode     = request.getParameter("postalCode");
        String      address        = request.getParameter("address");
        String      tell           = request.getParameter("tell");
        String      creditCard     = request.getParameter("creditCard");
        String      expirationDate = request.getParameter("expirationDate");
        String      holder         = request.getParameter("holder");
        String      securityCode   = request.getParameter("securityCode");

        if (!checkInputText(memberMail, memberPassword, memberName, postalCode, address, tell, creditCard, holder, securityCode)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_input.jsp").forward(request, response);
            return;
        }

        MemberBeans memberBeans = new MemberBeans();
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
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_check.jsp").forward(request, response);
    }


    public boolean checkInputText(String memberMail, String memberPassword, String memberName, String postalCode, String address, String tell, String creditCard, String holder, String securityCode) {
        if (!ErrorCheckService.checkLength(memberMail, 100, 1)) return false;
        if (!ErrorCheckService.checkLength(memberPassword, 128, 1)) return false;
        if (!ErrorCheckService.checkLength(memberName, 20, 1)) return false;
        if (!ErrorCheckService.checkLength(postalCode, 7, 7)) return false;
        if (!ErrorCheckService.checkLength(address, 50, 1)) return false;
        if (!ErrorCheckService.checkLength(tell, 11, 1)) return false;
        if (!ErrorCheckService.checkLength(creditCard, 16, 16)) return false;
        if (!ErrorCheckService.checkLength(holder, 20, 1)) return false;
        if (!ErrorCheckService.checkLength(securityCode, 3, 3)) return false;
        return true;
    }
}
