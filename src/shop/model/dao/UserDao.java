
package shop.model.dao;

import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase {
    public AdminBeans fetchAdminLogin(String adminMail, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AdminBeans adminBeans = null;

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
            adminBeans.setPostalCode(rs.getInt("postal_code"));
            adminBeans.setAddress(rs.getString("address"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminBeans;
    }

    public boolean deleteMember(MemberBeans memberBeans) {
        PreparedStatement stmt = null;
        int deleteLine = 0;

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
}
