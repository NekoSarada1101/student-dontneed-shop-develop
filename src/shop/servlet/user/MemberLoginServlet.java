package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;
import shop.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private UserService    userService    = new UserService();
    private ProductService productService = new ProductService();
    private Logger         logger         = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        HttpSession session     = request.getSession();
        MemberBeans memberLoginInfo = (MemberBeans) session.getAttribute("memberLoginInfo");
        session.removeAttribute("memberBeans");

        if (memberLoginInfo == null) {
            List<ProductBeans> productList = productService.fetchSearchProductList(/* genreCode= */0, /* sortColumn= */"product_id", /* sortOrder= */"desc", /* searchWord= */"");
            session.setAttribute("productList", productList);
            logger.info("productList.size={}", productList.size());

            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
        } else {
            logger.trace("{} End", ErrorCheckService.getMethodName());
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

        MemberBeans memberLoginInfo = userService.fetchMemberLogin(memberMail, memberPassword);
        logger.info("memberLoginInfo={}", memberLoginInfo);

        HttpSession session = request.getSession();
        if (memberLoginInfo == null) {
            //取得に失敗した場合
            request.setAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            logger.trace("{} End", ErrorCheckService.getMethodName());
            request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);

        } else {
            //取得に成功した場合
            session.setAttribute("memberLoginInfo", memberLoginInfo);
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
