
package shop.model.service;

import shop.model.bean.ProductBeans;
import shop.model.dao.ProductDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ProductService extends CommonService {

    private ProductDao productDao = new ProductDao();

    public boolean insertProduct(ProductBeans productBeans) {
        return productDao.insertProduct(productBeans);
    }

    public boolean updateProduct(ProductBeans productBeans) {
        return productDao.updateProduct(productBeans);
    }

    public boolean deleteProduct(ProductBeans productBeans) {
        return productDao.updateProduct(productBeans);
    }

    public List<Map<String, Object>> fetchGenreInfo() {
        return productDao.fetchGenreInfo();
    }

    public List<ProductBeans> fetchAdminProductList(String adminMail) {
        return productDao.fetchAdminProductList(adminMail);
    }

    public byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16777215];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public List<ProductBeans> fetchSearchProductList(int genreCode, String sortColumn, String sortOrder, String searchWord) throws IOException {
        return productDao.fetchSearchProductList(genreCode, sortColumn, sortOrder, searchWord);
    }
}
