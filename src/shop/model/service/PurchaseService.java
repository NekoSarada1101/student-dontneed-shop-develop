
package shop.model.service;

import shop.model.bean.ProductBeans;
import shop.model.bean.PurchaseDetailBeans;
import shop.model.dao.PurchaseDao;

import java.util.List;
import java.util.Map;

public class PurchaseService extends CommonService {

    PurchaseDao purchaseDao = new PurchaseDao();

    public List<ProductBeans> fetchCartList(String memberMail) {
        return purchaseDao.fetchCartList(memberMail);
    }

    public boolean insertCart(String memberMail, int productId) {
        return purchaseDao.insertCart(memberMail, productId);
    }

    public boolean deleteCart(String memberMail, int productId) {
        return purchaseDao.deleteCart(memberMail, productId);
    }

    public boolean checkExistsCart(int productId) {
        return purchaseDao.checkExistsCart(productId);
    }

    public Map<String, List<ProductBeans>> checkExistsStock(String memberMail) {
        return purchaseDao.checkExistsStock(memberMail);
    }

    public boolean insertPurchaseDetail(List<PurchaseDetailBeans> purchaseDetailList) {
        return purchaseDao.insertPurchaseDetail(purchaseDetailList);
    }

    public List<Map<String, Object>> fetchPurchaseHistory(String memberMail) {
        return purchaseDao.fetchPurchaseHistory(memberMail);
    }

    public List<Map<String, Object>> fetchSalesInfo(String adminMail) {
        return purchaseDao.fetchSalesInfo(adminMail);
    }
}
