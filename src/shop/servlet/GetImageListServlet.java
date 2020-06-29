package shop.servlet;

import shop.model.bean.ProductBeans;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@WebServlet("/getImageList")
public class GetImageListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ProductBeans> productList = (List<ProductBeans>) session.getAttribute("productList");

        int index = Integer.parseInt(request.getParameter("index"));

        InputStream inputStream = new ByteArrayInputStream(productList.get(index).getImage());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedImage img = ImageIO.read(bufferedInputStream);

        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(img, "png", outputStream);
        outputStream.flush();
    }
}
