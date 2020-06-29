package shop.servlet;

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

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/Test.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductService productService = new ProductService();
		HttpSession session = request.getSession();

		//int genreCode = request.getParameter("searchGenre");
		//String sortColumn  = request.getParameter("sortColumn");
		//String sortOrder   = request.getParameter("sortOrder");
		//String searchWord  = request.getParameter("searchWord");

		int    genreCode = 0000;
		String sortColumn  = "price";//pirice,product_id,product_name
		String sortOrder   = "ASC";//DESC
		String searchWord  = "制服";

		List<ProductBeans>productList = productService.fetchSearchProductList(genreCode, sortColumn, sortOrder, searchWord);
		session.setAttribute("productList", productList);
		request.getRequestDispatcher("WEB-INF/jsp/search_product_list.jsp").forward(request,response);
	}
}


