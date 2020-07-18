package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.service.CommonService;
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
        HttpSession session = request.getSession();
        logger.trace("{} Start", CommonService.getMethodName());
        MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");
        logger.info("memberBeans={}", memberBeans);


        userService.deleteMember(memberBeans);
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/admin/member_delete_complete.jsp").forward(request, response);
    }
}
