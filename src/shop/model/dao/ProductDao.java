
package shop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shop.model.bean.ProductBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;

public class ProductDao extends DaoBase {

    private Logger logger = LogManager.getLogger();

    public List<ProductBeans> fetchSearchProductList(int genreCode, String sortColumn, String sortOrder, String searchWord) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement  stmt        = null;
        ResultSet          rs          = null;
        List<ProductBeans> productList = null;

        try {
            this.connect();
            searchWord = "%" + searchWord + "%";

            if (genreCode == 0) { //ジャンルですべてを指定された場合
                stmt = con.prepareStatement("SELECT * FROM product WHERE is_sold = false AND product_name LIKE ? ORDER BY ? " + sortOrder);
                stmt.setString(1, searchWord);
                stmt.setString(2, sortColumn);
            } else {
                stmt = con.prepareStatement("SELECT * FROM product WHERE genre_code = ? AND is_sold = false AND product_name LIKE ? ORDER BY ? " + sortOrder);
                stmt.setInt(1, genreCode);
                stmt.setString(2, searchWord);
                stmt.setString(3, sortColumn);
            }
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            productList = new ArrayList<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));
                productList.add(productBeans);
            }
            logger.info("productList.size={}", productList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return productList;
    }


    public boolean insertProduct(ProductBeans productBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("INSERT INTO product (product_name, price, image, product_explanation, genre_code ,admin_mail) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, productBeans.getProductName());
            stmt.setInt(2, productBeans.getPrice());
            stmt.setBinaryStream(3, new ByteArrayInputStream(productBeans.getImage()));
            stmt.setString(4, productBeans.getProductExplanation());
            stmt.setInt(5, productBeans.getGenreCode());
            stmt.setString(6, productBeans.getAdminMail());
            insertLine = stmt.executeUpdate();
            logger.info("insertLine={}", insertLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return insertLine != 0;
    }


    public boolean updateProduct(ProductBeans productBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               updateLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE product SET product_name = ?, price = ?, image = ?, product_explanation = ?, genre_code = ?, is_sold = ? WHERE product_id = ?");
            stmt.setString(1, productBeans.getProductName());
            stmt.setInt(2, productBeans.getPrice());
            stmt.setBinaryStream(3, new ByteArrayInputStream(productBeans.getImage()));
            stmt.setString(4, productBeans.getProductExplanation());
            stmt.setBoolean(5, productBeans.getIsSold());
            stmt.setInt(6, productBeans.getGenreCode());
            stmt.setInt(7, productBeans.getProductId());
            updateLine = stmt.executeUpdate();
            logger.info("updateLine={}", updateLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return updateLine != 0;
    }


    public List<Map<String, Object>> fetchGenreInfo() {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement         stmt          = null;
        ResultSet                 rs            = null;
        List<Map<String, Object>> genreInfoList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM genre");
            rs = stmt.executeQuery();

            genreInfoList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> genreInfoMap = new HashMap<>();
                genreInfoMap.put("genreCode", rs.getInt("genre_code"));
                genreInfoMap.put("genreName", rs.getString("genre_name"));
                genreInfoList.add(genreInfoMap);
            }
            logger.info("genreInfoList.size={}", genreInfoList.size());

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return genreInfoList;
    }


    public List<ProductBeans> fetchAdminProductList(String adminMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement  stmt        = null;
        ResultSet          rs          = null;
        List<ProductBeans> productList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM product WHERE admin_mail = ?");
            stmt.setString(1, adminMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            productList = new ArrayList<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));
                productList.add(productBeans);
            }
            logger.info("productList.size={}", productList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return productList;
    }


    public boolean deleteProductAll(String adminMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE product SET is_sold = true WHERE admin_mail = ?");
            stmt.setString(1, adminMail);
            deleteLine = stmt.executeUpdate();
            logger.info("deleteLine={}", deleteLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return deleteLine != 0;
    }

    public List<ProductBeans> fetchAdminSearchProductList(String adminMail, int genreCode, String sortColumn, String sortOrder, String searchWord) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement  stmt        = null;
        ResultSet          rs          = null;
        List<ProductBeans> productList = null;

        try {
            this.connect();
            searchWord = "%" + searchWord + "%";

            if (genreCode == 0) { //ジャンルですべてを指定された場合
                stmt = con.prepareStatement("SELECT * FROM product WHERE admin_mail = ? AND is_sold = false AND product_name LIKE ? ORDER BY ? " + sortOrder);
                stmt.setString(1, adminMail);
                stmt.setString(2, sortColumn);
            } else {
                stmt = con.prepareStatement("SELECT * FROM product WHERE genre_code = ? AND is_sold = false AND product_name LIKE ? ORDER BY ? " + sortOrder);
                stmt.setInt(1, genreCode);
                stmt.setString(2, searchWord);
                stmt.setString(3, sortColumn);
            } else {
                stmt = con.prepareStatement("SELECT * FROM product WHERE admin_mail = ? AND genre_code = ? AND is_sold = false AND product_name LIKE ? ORDER BY ? " + sortOrder);
                stmt.setString(1, adminMail);
                stmt.setInt(2, genreCode);
                stmt.setString(3, searchWord);
                stmt.setString(4, sortColumn);
            }
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            productList = new ArrayList<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));
                productList.add(productBeans);
            }
            logger.info("productList.size={}", productList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return productList;
    }
}
