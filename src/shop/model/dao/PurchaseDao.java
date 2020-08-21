package shop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.ProductBeans;
import shop.model.bean.PurchaseDetailBeans;
import shop.model.service.ErrorCheckService;
import shop.model.service.ProductService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseDao extends DaoBase {

    private Logger logger = LogManager.getLogger();

    public List<ProductBeans> fetchCartList(String memberMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement  stmt     = null;
        ResultSet          rs       = null;
        List<ProductBeans> cartList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM cart c LEFT OUTER JOIN product p ON c.product_id = p.product_id WHERE c.member_mail = ?");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            cartList = new ArrayList<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("p.product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));
                cartList.add(productBeans);
            }
            logger.info("cartList.size={}", cartList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return cartList;
    }


    public boolean insertCart(String memberMail, int productId) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("INSERT INTO cart (member_mail, product_id) VALUES (?, ?)");
            stmt.setString(1, memberMail);
            stmt.setInt(2, productId);
            insertLine = stmt.executeUpdate();
            logger.info("insertLine={}", insertLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);

        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return insertLine != 0;
    }


    public boolean deleteCart(String memberMail, int productId) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("DELETE FROM cart WHERE member_mail = ? AND product_id = ?");
            stmt.setString(1, memberMail);
            stmt.setInt(2, productId);
            deleteLine = stmt.executeUpdate();
            logger.info("deleteLine={}", deleteLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return deleteLine != 0;
    }


    public boolean checkExistsCart(int productId) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt     = null;
        ResultSet         rs       = null;
        boolean           isExists = false;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM cart WHERE exists(SELECT * FROM cart c WHERE c.product_id = ?)");
            stmt.setInt(1, productId);
            rs = stmt.executeQuery();
            isExists = rs.next();
            logger.info("isExists={}", isExists);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return isExists;
    }


    public Map<String, List<ProductBeans>> checkExistsStock(String memberMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement               stmt         = null;
        ResultSet                       rs           = null;
        List<ProductBeans>              purchaseList;
        List<ProductBeans>              deleteList;
        Map<String, List<ProductBeans>> purchaseMap  = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM cart c LEFT OUTER JOIN product p ON c.product_id = p.product_id WHERE c.member_mail = ?");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            purchaseList = new ArrayList<>();
            deleteList = new ArrayList<>();
            purchaseMap = new HashMap<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("p.product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                if (productBeans.isSold()) { //購入済みなら
                    deleteList.add(productBeans);
                } else {  //購入されてないなら
                    purchaseList.add(productBeans);
                }
            }
            purchaseMap.put("purchaseList", purchaseList);
            purchaseMap.put("deleteList", deleteList);
            logger.info("purchaseMap={}", purchaseMap);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return purchaseMap;
    }


    public boolean insertPurchaseDetail(List<PurchaseDetailBeans> purchaseDetailBeansList) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            //SQL文生成
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < purchaseDetailBeansList.size(); i++) {
                stringBuilder.append("(?, ?, ?),");
            }
            //末尾の , を削除
            String placeHolder = stringBuilder.substring(0,stringBuilder.lastIndexOf(","));
            String sql = "INSERT INTO purchase_details (member_mail, product_id, purchase_date) VALUES " + placeHolder;

            stmt = con.prepareStatement(sql);
            for (PurchaseDetailBeans purchaseDetailBeans : purchaseDetailBeansList) {
                int parameterIndex = 1;
                stmt.setString(parameterIndex, purchaseDetailBeans.getMemberMail());
                stmt.setInt(parameterIndex + 1, purchaseDetailBeans.getProductId());
                stmt.setString(parameterIndex + 2, purchaseDetailBeans.getPurchaseDate());
                parameterIndex += 3;
            }
            insertLine = stmt.executeUpdate();
            logger.info("insertLine={}", insertLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);

        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return insertLine != 0;
    }


    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement         stmt         = null;
        ResultSet                 rs           = null;
        List<Map<String, Object>> purchaseList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM purchase_details p LEFT OUTER JOIN product pd ON p.product_id = pd.product_id WHERE p.member_mail = ?");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            purchaseList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> purchaseMap  = new HashMap<>();
                ProductBeans        productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                purchaseMap.put("productBeans", productBeans);
                purchaseMap.put("purchaseDate", rs.getString("purchase_date"));
                purchaseList.add(purchaseMap);
            }
            logger.info("purchaseList.size={}", purchaseList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);

        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return purchaseList;
    }


    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement         stmt      = null;
        ResultSet                 rs        = null;
        List<Map<String, Object>> salesList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT p.*, pd.purchase_date FROM purchase_details as pd LEFT OUTER JOIN product as p ON pd.product_id = p.product_id WHERE p.admin_mail = ?");
            stmt.setString(1, adminMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            salesList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> salesMap     = new HashMap<>();
                ProductBeans        productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                salesMap.put("productBeans", productBeans);
                salesMap.put("purchaseDate", rs.getString("purchase_date"));
                salesList.add(salesMap);
            }
            logger.info("salesList.size={}", salesList.size());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("error", e);

        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return salesList;
    }
}
