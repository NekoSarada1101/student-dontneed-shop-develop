
package shop.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private List<String> memberUrlPatterns = Arrays.asList("/memberLogout", "/memberTop", "/memberUpdateInput", "/memberUpdateCheck",
            "/memberUpdateComplete", "/memberDeleteCheck", "/memberDeleteComplete", "/productSearchAndDisplay", "/memberProductDetail", "/memberDetail", "/cartInsert", "/cartDisplay", "/cartDelete", "/purchaseCheck",
            "/purchaseComplete", "/purchaseHistory");

    private List<String> adminUrlPatterns = Arrays.asList("/adminLogout", "/adminTop", "/adminDetail", "/adminProductDetail", "/adminUpdateInput", "/adminUpdateCheck", "/adminUpdateComplete", "/productInsertInput", "/productInsertCheck", "/productInsertComplete", "/productUpdateInput", "/productUpdateCheck", "/productUpdateComplete", "/productDeleteCheck", "/productDeleteComplete", "/salesCheck");

    private Logger logger = LogManager.getLogger();

    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
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
                response.sendRedirect("loginFilterError");
            }
        } else if (adminUrlPatterns.contains(path)) {
            AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");
            if (adminBeans != null) {
                // セッションがNULLでなければ、通常どおりの遷移
                chain.doFilter(req, res);
            } else {
                // セッションがNullならば、エラー画面へ飛ばす
                response.sendRedirect("loginFilterError");
            }
        } else if (path.equals("/memberLogin") || path.contains("/memberInsert") || path.equals("/adminLogin") || path.contains("/adminInsert") || path.contains("Image") || path.contains("css") || path.contains("js") || path.equals("/loginFilterError")) {
            chain.doFilter(req, res);
        } else {
            logger.fatal("フィルター対象のファイルではありません：{}", path);

            response.sendRedirect("loginFilterError");
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
