package shop.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;



@WebServlet("/genreSearch")
public class GenreSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int genreCode = Integer.parseInt(request.getParameter("genreCode"));
		List<ProductBeans> prodectList =  productService.fetchSearchProductList(genreCode,"product_id","desc","");
		session.setAttribute("productList", prodectList);
        request.getRequestDispatcher("WEB-INF/jsp/user/search_product_list.jsp").forward(request, response);
	}

}
