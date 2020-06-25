package shop.model.dao;

import java.sql.SQLException;

import shop.model.bean.AdminBeans;

public class UserDao extends DaoBase {
	public AdminBeans fetchAdminLogin(String admin_mail,String password){
		//戻り値を初期化
		AdminBeans adminBeans = null;
		try{
			//DBのオープン
			super.connect();
			//SQLを作成
			String sql = "SELECT * FROM student_dontneed_shop WHERE admin_mail=? AND admin_password=?";
			stmt = this.con.prepareStatement(sql);
			stmt.setString(1,admin_mail);
			stmt.setString(2, password);

			//SQLの発行
			rs = this.stmt.executeQuery();
			rs.next();

			//beanへ格納(検索失敗ならrs.nextでcatchへ飛ぶ)
			adminBeans = new AdminBeans();
			adminBeans.setAdminMail(this.rs.getString("adminMail"));
			adminBeans.setAdminPassword(this.rs.getString("adminPassword"));

		}catch (SQLException e) {
			e.printStackTrace();
			adminBeans = null;
		}finally {

			try {
				super.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return adminBeans;
	}
}
