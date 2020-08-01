package shop.model.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminBeans implements Serializable {
    private String adminMail     = null;
    private String adminPassword = null;
    private String adminName     = null;
    private String postalCode    = null;
    private String address       = null;
}
