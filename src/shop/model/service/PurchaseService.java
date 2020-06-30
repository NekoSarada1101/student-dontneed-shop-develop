package shop.model.service;

import shop.model.dao.PurchaseDao;

import java.util.List;
import java.util.Map;

public class PurchaseService extends CommonService {

    PurchaseDao purchaseDao = new PurchaseDao();

    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        return purchaseDao.fetchSalesInfo(adminMail);
    }

    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        return purchaseDao.fetchPurchaseHistory(memberMail);
    }
}
