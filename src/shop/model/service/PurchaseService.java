package shop.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import shop.model.dao.PurchaseDao;

public class PurchaseService extends CommonService {

    PurchaseDao purchaseDao = new PurchaseDao();

    public List<ProductBeans> fetchCartList(String memberMail) {
        return purchaseDao.fetchCartList(memberMail);
    }

    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        return purchaseDao.fetchSalesInfo(adminMail);
    }


    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        return purchaseDao.fetchPurchaseHistory(memberMail);
    }

    public boolean insertCart(String memberMail,int productId) throws IOException {
    	return purchaseDao.insertCart(memberMail, productId);
    }
}
