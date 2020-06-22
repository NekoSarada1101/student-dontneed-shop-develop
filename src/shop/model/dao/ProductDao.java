
package shop.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.model.bean.ProductBeans;

public class ProductDao extends DaoBase {

    public List<Map<String, Object>> fetchGenreInfo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return genreInfoList;
    }

    public List<ProductBeans> fetchAdminProductList(String adminMail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ProductBeans> productList = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM product WHERE admin_mail = ?");
            stmt.setString(1, adminMail);
            rs = stmt.executeQuery();

            productList = new ArrayList<>();

            while (rs.next()) {
                ProductBeans productBeans = new ProductBeans();
                productBeans.setProductId(rs.getInt("product_id"));
                productBeans.setProductName(rs.getString("product_name"));
                productBeans.setPrice(rs.getInt("price"));
                //TODO 画像取得処理
                productBeans.setProductExplanation(rs.getString("product_explanation"));
                productBeans.setIsSold(rs.getBoolean("is_sold"));
                productBeans.setGenreCode(rs.getInt("genre_code"));
                productBeans.setAdminMail(rs.getString("admin_mail"));

                productList.add(productBeans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}
