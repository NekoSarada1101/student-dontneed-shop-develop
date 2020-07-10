package shop.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memberInsertInput")
public class MemberInsertInputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		// member_insert_input.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user/member_insert_input.jsp");
				dispatch.forward(request, response);
	}
}
