
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
            stmt.setInt(4, memberBeans.getPostalCode());
            stmt.setString(5, memberBeans.getAddress());
            stmt.setLong(6, memberBeans.getTell());
            stmt.setLong(7, memberBeans.getCreditCard());
            stmt.setString(8, memberBeans.getExpirationDate());
            stmt.setString(9, memberBeans.getHolder());
            stmt.setInt(10, memberBeans.getSecurityCode());
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
            adminBeans.setPostalCode(rs.getInt("postal_code"));
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
public boolean updateMember(MemberBeans membeerBeans){

	DaoBase daoBase = new DaoBase();

	daoBase.connect();

	boolean check = false;

	//変数定義
    PreparedStatement ps = null;

    try {
    //SQL文を定義する
    String sql = "UPDATE member SET member_mail = ?, member_password = ?, member_name = ?,  address = ?, postal_code = ?, credit_card = ?, tell = ?, expiration_date = ?,security_code = ?, holder = ? WHERE member_mail  = ?";


        //実行するSQL文とパラメータを指定する

			ps = con.prepareStatement(sql);

        ps.setString(1, membeerBeans.getMemberMail());
        ps.setString(2, membeerBeans.getMemberPassword());
        ps.setString(3, membeerBeans.getMemberName());
        ps.setString(4, membeerBeans.getAddress());
        ps.setInt(5, membeerBeans.getPostalCode());
        ps.setInt(6, membeerBeans.getCreditCard());
        ps.setInt(7, membeerBeans.getTell());
        ps.setString(8, membeerBeans.getExpirationDate());
        ps.setInt(9, membeerBeans.getSecurityCard());
        ps.setString(10, membeerBeans.getHolder());
        ps.setString(11, membeerBeans.getMemberMail());


        //INSERT文を実行する
        int i = ps.executeUpdate();

        if(i != 0) {
    		check = true;
    	}else {
    		check = false;
    	}

    } catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}




	daoBase.close();
	return check;
}

}
