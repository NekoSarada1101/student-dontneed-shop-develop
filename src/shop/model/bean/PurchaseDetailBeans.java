package shop.model.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurchaseDetailBeans implements Serializable {
    private int    purchaseId   = 0;
    private String memberMail   = null;
    private int    productId    = 0;
    private String purchaseDate = null;
}
