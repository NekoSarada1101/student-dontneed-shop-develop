
package shop.model.dao;

import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase {
    public boolean checkMemberMailExists(String memberMail) {
        PreparedStatement stmt = null;
        ResultSet         rs   = null;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM member WHERE member_mail = ?");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();
            rs.next();
            rs.getString("member_mail");

        } catch (SQLException e) {
            e.printStackTrace();
            return /* isExists = */ false;
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return /* isExists = */ true;
    }

    public boolean insertMember(MemberBeans memberBeans) {
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("INSERT INTO member (member_mail, member_password, member_name, postal_code, address, tell, credit_card, expiration_date, holder, security_code) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, memberBeans.getMemberMail());
            stmt.setString(2, memberBeans.getMemberPassword());
            stmt.setString(3, memberBeans.getMemberName());
            stmt.setString(4, memberBeans.getPostalCode());
            stmt.setString(5, memberBeans.getAddress());
            stmt.setString(6, memberBeans.getTell());
            stmt.setString(7, memberBeans.getCreditCard());
            stmt.setString(8, memberBeans.getExpirationDate());
            stmt.setString(9, memberBeans.getHolder());
            stmt.setString(10, memberBeans.getSecurityCode());
            insertLine = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertLine != 0;
    }

    public boolean updateMember(MemberBeans memberBeans) {
        PreparedStatement stmt       = null;
        int               updateLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE member SET member_mail = ?, member_password = ?, member_name = ?, postal_code = ?, address = ?, tell = ?, credit_card = ?, expiration_date = ?, holder = ?, security_code = ? WHERE member_mail = ?");
            stmt.setString(1, memberBeans.getMemberMail());
            stmt.setString(2, memberBeans.getMemberPassword());
            stmt.setString(3, memberBeans.getMemberName());
            stmt.setString(4, memberBeans.getPostalCode());
            stmt.setString(5, memberBeans.getAddress());
            stmt.setString(6, memberBeans.getTell());
            stmt.setString(7, memberBeans.getCreditCard());
            stmt.setString(8, memberBeans.getExpirationDate());
            stmt.setString(9, memberBeans.getHolder());
            stmt.setString(10, memberBeans.getSecurityCode());
            stmt.setString(11, memberBeans.getMemberMail());
            updateLine = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updateLine != 0;
    }

    public boolean deleteMember(MemberBeans memberBeans) {
        PreparedStatement stmt       = null;
        int               deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("DELETE FROM member WHERE member_mail = ?");
            stmt.setString(1, memberBeans.getMemberMail());
            deleteLine = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteLine != 0;
    }

    public AdminBeans fetchAdminLogin(String adminMail, String password) {
        PreparedStatement stmt       = null;
        ResultSet         rs         = null;
        AdminBeans        adminBeans = null;
        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM admin WHERE admin_mail = ? AND admin_password = ?");
            stmt.setString(1, adminMail);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            rs.next();

            adminBeans = new AdminBeans();
            adminBeans.setAdminMail(rs.getString("admin_mail"));
            adminBeans.setAdminName(rs.getString("admin_name"));
            adminBeans.setAdminPassword(rs.getString("admin_password"));
            adminBeans.setPostalCode(rs.getString("postal_code"));
            adminBeans.setAddress(rs.getString("address"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminBeans;
    }
}
