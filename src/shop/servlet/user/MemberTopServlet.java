package shop.servlet.user;

import shop.model.bean.MemberBeans;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/memberTop")
public class MemberTopServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> genreInfoList = productService.fetchGenreInfo();

        HttpSession session = request.getSession();

        //test//
        MemberBeans memberBeans = new MemberBeans();
        memberBeans.setMemberName("原田遼汰");
        session.setAttribute("member", memberBeans);
        //test//

        //TODO fetchSearchProductListメソッドを呼び出し

        request.getRequestDispatcher("WEB-INF/jsp/user/member_top.jsp").forward(request, response);
    }
}
