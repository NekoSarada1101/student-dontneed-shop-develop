
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

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    private Logger      logger      = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());

        String memberMail     = request.getParameter("memberMail");
        String memberPassword = request.getParameter("memberPassword");
        logger.info("memberMail={}", memberMail);

        if (!checkInputText(memberMail, memberPassword)) {
            logger.trace("{} End", CommonService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }

        MemberBeans memberBeans = userService.fetchMemberLogin(memberMail, memberPassword);
        logger.info("memberBeans={}", memberBeans);

        //遷移先
        HttpSession session = request.getSession();
        if (memberBeans == null) { //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            logger.trace("{} End", CommonService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
        } else {
            //取得した場合
            session.setAttribute("memberLoginInfo", memberBeans);
            logger.trace("{} End", CommonService.getMethodName());
            response.sendRedirect("memberTop");
        }
    }

    public boolean checkInputText(String memberMail, String memberPassword) {
        if (!userService.checkLength(memberMail, 100, 1))
            return false;
        if (!userService.checkLength(memberPassword, 128, 1))
            return false;
        return true;
    }
}
