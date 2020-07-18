
package shop.filter;

import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    private List<String> memberUrlPatterns = Arrays.asList("/memberTop", "/memberInsertInput", "/memberInsertCheck", "/memberInsertComplete", "/memberUpdateInput", "/memberUpdateCheck",
            "/memberUpdateComplete", "/memberDeleteCheck", "/memberDeleteComplete", "/productSearchAndDisplay", "/memberProductDetail", "/memberDetail", "/cartInsert", "/cartDisplay", "/purchaseCheck",
            "/purchaseComplete");
    private List<String> adminUrlPatterns  = Arrays.asList("/adminTop", "/adminProductDetail", "/productInsertInput", "/productInsertCheck", "/productInsertComplete", "/productUpdateInput", "/productUpdateCheck", "/productUpdateComplete", "/productDeleteCheck", "/productDeleteComplete", "/salesCheck");

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here

        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession         session  = request.getSession();

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        if (memberUrlPatterns.contains(path)) {
            MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");
            if (memberBeans != null) {
                // セッションがNULLでなければ、通常どおりの遷移
                chain.doFilter(req, res);
            } else {
                // セッションがNullならば、エラー画面へ飛ばす
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
                dispatcher.forward(req, res);
            }
        } else if (adminUrlPatterns.contains(path)) {
            AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");
            if (adminBeans != null) {
                // セッションがNULLでなければ、通常どおりの遷移
                chain.doFilter(req, res);
            } else {
                // セッションがNullならば、エラー画面へ飛ばす
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
                dispatcher.forward(req, res);
            }
        } else if (path.equals("/memberLogin") || path.equals("/adminLogin") || path.contains("Image") || path.contains("css") || path.contains("js")) {
            chain.doFilter(req, res);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
