package shop.servlet.user;

import java.io.IOException;
import java.util.logging.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.MemberBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.UserService;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    private Logger      logger      = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	MemberBeans memberBeans = (MemberBeans)session.getAttribute("memberLoginInfo");
    	if(memberBeans == null) {
    		logger.trace("{} Start", ErrorCheckService.getMethodName());
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
    	}else {
    		response.sendRedirect("memberTop");
    	}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        String memberMail     = request.getParameter("memberMail");
        String memberPassword = request.getParameter("memberPassword");

        if (!checkInputTextLength(memberMail, memberPassword)) {
            request.setAttribute("errorMessage", "不正な入力です");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
            return;
        }

        MemberBeans memberBeans = userService.fetchMemberLogin(memberMail, memberPassword);
        logger.info("memberBeans={}", memberBeans);

        HttpSession session = request.getSession();
        if (memberBeans == null) {
            //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);

        } else {
            //取得に成功した場合
            session.setAttribute("memberLoginInfo", memberBeans);
            logger.trace("{} End", ErrorCheckService.getMethodName());
            response.sendRedirect("memberTop");
        }
    }


    public boolean checkInputTextLength(String memberMail, String memberPassword) {
        if (!ErrorCheckService.checkLength(memberMail, /* maxLength= */100, /* minLength= */1)) {
            return false;
        } else if (!ErrorCheckService.checkLength(memberPassword, 128, 1)) {
            return false;
        }
        return true;
    }
}
