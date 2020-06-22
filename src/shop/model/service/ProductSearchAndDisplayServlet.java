package shop.model.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductSearchAndDisplayServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		
		String searchGenre = request.getParameter("searchGenre");
		String sortColumu  = request.getParameter("sortColumu");
		String sortOrder   = request.getParameter("sortOrder");
		String searchWord  = request.getParameter("searchWord");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}


