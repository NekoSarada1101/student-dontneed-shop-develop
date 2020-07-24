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

@WebServlet("/memberDeleteComplete")
public class MemberDeleteCompleteServlet extends HttpServlet {

    UserService userService = new UserService();
    private Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session = request.getSession();
        MemberBeans memberLoginInfo = (MemberBeans) session.getAttribute("memberLoginInfo");
        logger.info("memberLoginInfo={}", memberLoginInfo);

        userService.deleteMember(memberLoginInfo);
        logger.trace("{} End", ErrorCheckService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_delete_complete.jsp").forward(request, response);
    }
}
