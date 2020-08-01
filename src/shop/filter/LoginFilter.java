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

    private List<String> memberUrlPatterns = Arrays.asList(
            "/memberTop",
            "/productSearchAndDisplay", "/genreSearch", "/productListPagination",
            "/memberProductDetail",
            "/memberDetail",
            "/memberUpdateInput", "/memberUpdateCheck", "/memberUpdateComplete",
            "/memberDeleteCheck", "/memberDeleteComplete",
            "/cartInsert", "/cartDisplay", "/cartDelete",
            "/purchaseCheck", "/purchaseComplete", "/purchaseHistory"
    );

    private List<String> adminUrlPatterns = Arrays.asList(
            "/adminTop",
            "/adminProductSearchAndDisplay", "/adminProductListPagination",
            "/adminProductDetail",
            "/adminDetail",
            "/adminUpdateInput", "/adminUpdateCheck", "/adminUpdateComplete",
            "/adminDeleteCheck", "/adminDeleteComplete",
            "/productInsertInput", "/productInsertCheck", "/productInsertComplete",
            "/productUpdateInput", "/productUpdateCheck", "/productUpdateComplete",
            "/productDeleteCheck", "/productDeleteComplete",
            "/salesCheck"
    );

    private List<String> notFilterUrlPatterns = Arrays.asList(
            "/memberLogin", "/adminLogin",
            "/memberLogout", "/adminLogout",
            "/memberInsertInput", "/memberInsertCheck", "/memberInsertComplete",
            "adminInsertInput", "adminInsertCheck", "adminInsertComplete",
            "/getImage", "/getImageList"
    );

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

        String path = request.getRequestURI().substring(request.getContextPath().length());
        path = path.substring(path.lastIndexOf("/"));

        if (memberUrlPatterns.contains(path)) {
            MemberBeans memberBeans = (MemberBeans) session.getAttribute("memberLoginInfo");
            if (memberBeans != null) {
                // セッションがNULLでなければ、通常どおりの遷移
                chain.doFilter(req, res);
                return;

            } else {
                // セッションがNullならば、エラー画面へ飛ばす
                logger.debug(path + " memberLoginInfo is null");
                request.setAttribute("errorMessage", "セッションタイムアウト、または不正な画面遷移が発生しました。");

                request.getRequestDispatcher("WEB-INF/jsp/user/member_login.jsp").forward(request, response);
                return;
            }
        }

        if (adminUrlPatterns.contains(path)) {
            AdminBeans adminBeans = (AdminBeans) session.getAttribute("adminLoginInfo");
            if (adminBeans != null) {
                // セッションがNULLでなければ、通常どおりの遷移
                chain.doFilter(req, res);
                return;

            } else {
                // セッションがNullならば、エラー画面へ飛ばす
                logger.debug(path + " adminLoginInfo is null");
                request.setAttribute("errorMessage", "セッションタイムアウト、または不正な画面遷移が発生しました。");

                request.getRequestDispatcher("WEB-INF/jsp/admin/admin_login.jsp").forward(request, response);
                return;
            }
        }

        if (notFilterUrlPatterns.contains(path) || path.contains("css") || path.contains("js") || path.contains("png")) {
            chain.doFilter(req, res);
        } else {
            logger.fatal("フィルター対象のファイルではありません：{}", path);
            chain.doFilter(req, res);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }
}

