package shop.model.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberBeans implements Serializable {
    private String memberMail     = null;
    private String memberPassword = null;
    private String memberName     = null;
    private String postalCode     = null;
    private String address        = null;
    private String tell           = null;
    private String creditCard     = null;
    private String expirationDate = null;
    private String holder         = null;
    private String securityCode   = null;
}
