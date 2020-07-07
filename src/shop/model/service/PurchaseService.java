
package shop.model.service;

import shop.model.bean.ProductBeans;
import shop.model.dao.PurchaseDao;

import java.util.List;
import java.util.Map;

public class PurchaseService extends CommonService {

    PurchaseDao purchaseDao = new PurchaseDao();

    public Map<String, List<ProductBeans>> checkExistsStock(String memberMail) {
        return purchaseDao.checkExistsStock(memberMail);
    }

    public List<ProductBeans> fetchCartList(String memberMail) {
        return purchaseDao.fetchCartList(memberMail);
    }

    public boolean deleteCart(String memberMail, int productId) {
        return purchaseDao.deleteCart(memberMail, productId);
    }

    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        return purchaseDao.fetchSalesInfo(adminMail);
    }

    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        return purchaseDao.fetchPurchaseHistory(memberMail);
    }
}
