package shop.model.bean;

public class ProductBeans {
    private int productId = 0;
    private String productName = null;
    private int price = 0;
    private String productExplanation = null;
    private boolean isSold = false;
    private int genreCode = 0;
    private String adminMail = null;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductExplanation() {
        return productExplanation;
    }

    public void setProductExplanation(String productExplanation) {
        this.productExplanation = productExplanation;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean isSold) {
        this.isSold = isSold;
    }

    public int getGenreCode() {
        return genreCode;
    }

    public void setGenreCode(int genreCode) {
        this.genreCode = genreCode;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }
}
