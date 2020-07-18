package shop.servlet.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.service.CommonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/memberDetail")
public class MemberDetailServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("{} Start", CommonService.getMethodName());
        logger.trace("{} End", CommonService.getMethodName());
        request.getRequestDispatcher("WEB-INF/jsp/user/member_detail.jsp").forward(request, response);
    }
}
