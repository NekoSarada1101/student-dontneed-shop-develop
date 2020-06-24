
package shop.servlet;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        InputStream inputStream = new ByteArrayInputStream(((ProductBeans) session.getAttribute("productBeans")).getImage());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedImage img = ImageIO.read(bufferedInputStream);

        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(img, "png", outputStream);
        outputStream.flush();
    }
}
