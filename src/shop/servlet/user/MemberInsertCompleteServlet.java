package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/memberInsertComplete")
public class MemberInsertCompleteServlet extends HttpServlet {

    UserService userService = new UserService();
    private Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session     = request.getSession();
        MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberBeans");
        logger.info("memberBeans={}", memberBeans);

        if (userService.checkMemberMailExists(memberBeans.getMemberMail())) {
            String errorMessage = "そのメールアドレスは既に登録済みです";
            request.setAttribute("errorMessage", errorMessage);
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_input.jsp").forward(request, response);
            return;
        }

        userService.insertMember(memberBeans);

        session.removeAttribute("memberBeans");
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_complete.jsp").forward(request, response);
    }
}
