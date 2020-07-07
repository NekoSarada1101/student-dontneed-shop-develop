
package shop.model.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.model.bean.ProductBeans;
import shop.model.service.ProductService;

public class PurchaseDao extends DaoBase {

    public Map<String, List<ProductBeans>> checkExistsStock(String memberMail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ProductBeans> purchaseList = null;
        List<ProductBeans> deleteList = null;
        Map<String, List<ProductBeans>> purchaseMap = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM cart c LEFT OUTER JOIN product p ON c.product_id = p.product_id WHERE c.member_mail = ?");
            stmt.setString(1,memberMail);
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
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                if (productBeans.getIsSold()) { //購入済みなら
                    deleteList.add(productBeans);
                } else {  //購入されてないなら
                    purchaseList.add(productBeans);
                }
            }
            purchaseMap.put("purchaseList", purchaseList);
            purchaseMap.put("deleteList", deleteList);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return purchaseMap;
    }

    public List<ProductBeans> fetchCartList(String memberMail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
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
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                cartList.add(productBeans);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;
    }

    public boolean deleteCart(String memberMail, int productId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("DELETE FROM cart WHERE member_mail = ? AND product_id = ?");
            stmt.setString(1, memberMail);
            stmt.setInt(2, productId);
            deleteLine = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteLine != 0;
    }

    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> salesList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT p.*, pd.purchase_date FROM purchase_details as pd LEFT OUTER JOIN product as p ON pd.product_id = p.product_id WHERE p.admin_mail = ?");
            stmt.setString(1, adminMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            salesList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> salesMap = new HashMap<>();

                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                salesMap.put("productBeans", productBeans);
                salesMap.put("purchaseDate", rs.getString("purchase_date"));

                salesList.add(salesMap);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salesList;
    }

    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> purchaseList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM purchase_details p LEFT OUTER JOIN product pd ON p.product_id = pd.product_id WHERE p.member_mail = ?");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();

            ProductService productService = new ProductService();
            purchaseList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> purchaseMap = new HashMap<>();

                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                purchaseMap.put("productBeans", productBeans);
                purchaseMap.put("purchaseDate", rs.getString("purchase_date"));

                purchaseList.add(purchaseMap);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return purchaseList;
    }
    public boolean insertCart(String memberMail,int productId) throws IOException {
    	PreparedStatement stmt = null;
        int insertLine = 0;

        try {
        	stmt =  con.prepareStatement("INSERT INTO cart  (mamber_mail, product_id) VALUES (?, ?)");
        	this.connect();
        	stmt.setString(1,memberMail);
        	stmt.setInt(2,productId);
        	insertLine = stmt.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();

        }finally {
        	try {
        		this.close();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        if(insertLine >= 1) {
        	return true;
        }else{
        	return false;
        };
    }
}
