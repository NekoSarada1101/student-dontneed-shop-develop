package shop.model.service;

import shop.model.dao.ProductDao;

import java.util.List;
import java.util.Map;

public class ProductService extends CommonService {

    private ProductDao productDao = new ProductDao();

    public List<Map<String, Object>> fetchGenreInfo() {
        return productDao.fetchGenreInfo();
    }
}
