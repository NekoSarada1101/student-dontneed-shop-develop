package shop.model.dao;

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






}
