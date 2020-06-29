
package shop.model.dao;

<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.model.service.MemberBeans;

public class UserDao extends DaoBase {


	public boolean checkMemberMailExists(String memberMail) {

		DaoBase daoBase = new DaoBase();

		boolean check = false;

		daoBase.connect();

		//変数定義
        PreparedStatement ps = null;


        //SQL文を定義する
        String sql = "SELECT * FROM member WHERE member_mail = ?";


            //実行するSQL文とパラメータを指定する
            ps = con.prepareStatement(sql);
            ps.setString(1, memberMail);

            //INSERT文を実行する
            ResultSet rs = ps.executeQuery();

            	if(rs.next()) {
            		check = true;
            	}else {
            		check = false;
            	}

            //コミット
            con.commit();


		daoBase.close();
		return check;
	}





	public boolean insertMember(MemberBeans membeerBeans){

		DaoBase daoBase = new DaoBase();

		daoBase.connect();

		//変数定義
        PreparedStatement ps = null;


        //SQL文を定義する
        String sql = "INSERT INTO member  (member_mail,  member_password, member_name, credit_card, address, postal_code, tell, deadline, security_code, security_name) values(?,?,?,?,?,?,?,?,?,?)";


            //実行するSQL文とパラメータを指定する
            ps = con.prepareStatement(sql);
            ps.setString(1, membeerBeans.get);
            ps.setString(2, membeerBeans.get);
            ps.setString(3, membeerBeans.get);
            ps.setString(4, membeerBeans.get);
            ps.setString(5, membeerBeans.get);
            ps.setInt(6, membeerBeans.get);
            ps.setInt(7, membeerBeans.get);
            ps.setDate(8, membeerBeans.get);
            ps.setInt(9, membeerBeans.get);
            ps.setString(10, membeerBeans.get);

            //INSERT文を実行する
            int i = ps.executeUpdate();


            //コミット
            con.commit();


		daoBase.close();
		return true;
	}






=======
import shop.model.bean.AdminBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase {
    public AdminBeans fetchAdminLogin(String adminMail, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //戻り値を初期化
        AdminBeans adminBeans = null;
        try {
            //DBのオープン
            this.connect();
            //SQLを作成
            String sql = "SELECT * FROM admin WHERE admin_mail = ? AND admin_password = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, adminMail);
            stmt.setString(2, password);

            //SQLの発行
            rs = stmt.executeQuery();
            rs.next();

            //beanへ格納(検索失敗ならrs.nextでcatchへ飛ぶ)
            adminBeans = new AdminBeans();
            adminBeans.setAdminMail(rs.getString("admin_mail"));
            adminBeans.setAdminPassword(rs.getString("admin_password"));

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
>>>>>>> master
}
