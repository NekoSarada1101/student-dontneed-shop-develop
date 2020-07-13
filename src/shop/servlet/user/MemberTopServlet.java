
package shop.servlet.user;

import shop.model.bean.MemberBeans;
import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/memberTop")
public class MemberTopServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //test//
        MemberBeans memberBeans = new MemberBeans();
        memberBeans.setMemberName("原田遼汰");
        memberBeans.setMemberMail("ryouta@gmail.com");
        memberBeans.setMemberPassword("ryouta");
        memberBeans.setPostalCode("0987654");
        memberBeans.setAddress("aiueo");
        memberBeans.setTell("09812345678");
        memberBeans.setCreditCard("1234567812345678");
        memberBeans.setExpirationDate("2020-01-01");
        memberBeans.setHolder("HARADA RYOUTA");
        memberBeans.setSecurityCode("012");
        session.setAttribute("memberLoginInfo", memberBeans);
        //test//

        List<ProductBeans> productList = productService.fetchSearchProductList(0, "product_id", "desc", "");
        session.setAttribute("productList", productList);

        request.getRequestDispatcher("WEB-INF/jsp/user/member_top.jsp").forward(request, response);
    }
}
