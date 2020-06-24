package shop.model.service;

import shop.model.bean.ProductBeans;
import shop.model.dao.ProductDao;

import java.util.List;
import java.util.Map;

public class ProductService extends CommonService {

    private ProductDao productDao = new ProductDao();

    public boolean insertProduct(ProductBeans productBeans) {
        return productDao.insertProduct(productBeans);
    }

    public List<Map<String, Object>> fetchGenreInfo() {
        return productDao.fetchGenreInfo();
    }

    public List<ProductBeans> fetchAdminProductList(String adminMail){
        return productDao.fetchAdminProductList(adminMail);
    }
}
