package shop.servlet.admin;

import shop.model.service.PurchaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/salesCheck")
public class SalesCheckServlet extends HttpServlet {

    PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/admin/sales_check.jsp").forward(request, response);
    }
}
