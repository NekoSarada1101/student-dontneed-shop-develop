
package shop.model.dao;

import java.io.ByteArrayInputStream;
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

public class ProductDao extends DaoBase {
	ProductBeans productBeans = null;


	public List<ProductBeans> fetchSearchProductList(int genreCode,String sortColumn,String sortOrder,String searchWord){
		List<ProductBeans> list = new ArrayList<ProductBeans>();

		try {
			super.DbOpen();
			String sql = "SELECT * FROM product WHERE genre_code = ? AND is_sold = false AND  product_name LIKE '% ? %' AND product_explanation LIKE '% ? % ORDER BY ? ?";
			stmt = con.prepareStatement(sql);
        	stmt.setInt(1,genreCode);
        	stmt.setString(2,searchWord);
        	stmt.setString(3,searchWord);
        	stmt.setString(4,sortColumn);
        	stmt.setString(5,sortOrder);

        	rs = this.stmt.executeQuery();
        	while(rs.next()) {
        		productBeans = new ProductBeans();
        		productBeans.setProductId(rs.getInt("productId"));
        		productBeans.setProductName(rs.getString("productName"));
        		productBeans.setPrice(rs.getInt("price"));
        		productBeans.setImage(rs.getString("image"));
        		productBeans.setProdudctExplanation(rs.getString("productExplanation"));
        		productBeans.setGenreCode(rs.getString("genreCode"));
        		list.add(productBeans);
        	}
        	stmt.close();

		}catch(SQLException e) {
			productBeans = null;
		}finally {

			try {
				super.DbClose();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

    public boolean insertProduct(ProductBeans productBeans) {
        PreparedStatement stmt = null;
        int insertLine = 0;

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertLine != 0;
    }


    public boolean updateProduct(ProductBeans productBeans) {
        PreparedStatement stmt = null;
        int updateLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE product SET product_name = ?, price = ?, image = ?, product_explanation = ?, genre_code = ? WHERE product_id = ?");
            stmt.setString(1, productBeans.getProductName());
            stmt.setInt(2, productBeans.getPrice());
            stmt.setBinaryStream(3, new ByteArrayInputStream(productBeans.getImage()));
            stmt.setString(4, productBeans.getProductExplanation());
            stmt.setInt(5, productBeans.getGenreCode());
            stmt.setInt(6, productBeans.getProductId());
            updateLine = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updateLine != 0;
    }

    public boolean deleteProduct(ProductBeans productBeans) {
        PreparedStatement stmt = null;
        int deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE product SET is_sold = true WHERE product_id = ?");
            stmt.setInt(1, productBeans.getProductId());
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

        } catch (SQLException | IOException e) {
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

	public List<ProductBeans> fetchSearchProductList(int genreCode,String sortColumn,String sortOrder,String searchWord){
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		List<ProductBeans> list = new ArrayList<ProductBeans>();

		try {
			this.connect();
			String sql = "SELECT * FROM product WHERE genre_code = ? AND is_sold = false AND  product_name LIKE '% ? %' AND product_explanation LIKE '% ? % ORDER BY ? ?";
			stmt = con.prepareStatement(sql);
        	stmt.setInt(1,genreCode);
        	stmt.setString(2,searchWord);
        	stmt.setString(3,searchWord);
        	stmt.setString(4,sortColumn);
        	stmt.setString(5,sortOrder);

        	rs = stmt.executeQuery();

        	ProductService productService = new ProductService();


        	while(rs.next()) {
        		ProductBeans productBeans = new ProductBeans();
        		productBeans.setProductId(rs.getInt("productId"));
        		productBeans.setProductName(rs.getString("productName"));
        		productBeans.setPrice(rs.getInt("price"));
        		productBeans.setImage(productService.convertInputStreamToByteArray(rs.getBinaryStream("image")));
        		productBeans.setProductExplanation(rs.getString("product_explanation"));
        		productBeans.setGenreCode(rs.getInt("genre_code"));
        		list.add(productBeans);
        	}
        	stmt.close();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				this.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
}
