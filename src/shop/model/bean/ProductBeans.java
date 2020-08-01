package shop.model.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductBeans implements Serializable {
    private int     productId          = 0;
    private String  productName        = null;
    private int     price              = 0;
    private byte[]  image              = null;
    private String  productExplanation = null;
    private boolean isSold             = false;
    private int     genreCode          = 0;
    private String  adminMail          = null;
}
