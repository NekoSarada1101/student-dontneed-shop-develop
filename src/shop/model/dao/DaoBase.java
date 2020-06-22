package shop.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DaoBase {
	Connection con = null;			//DBMSへの接続や切断を行う
	PreparedStatement stmt = null;	//SQLの送信を行う
	ResultSet rs = null;			//DBMSからの検索結果を受け取る
	int rsno = 0;					//DBMSからの処理件数を受け取る

	//データソース
	DataSource ds = null;

	public Connection DbOpen(){
		try{
			//JDBCドライバのロード(java8から省略可)
			//JDBCドライバのロード(connector8.xからcj必要)
			Class.forName("student_dontneed_shop");

			//指定するデータベースへ接続(引数:URL/利用ユーザ/パスワード)
			//WindowsはMySqlのタイムゾーンの違いでタイム指定が必要
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/diary1901301?characterEncoding=UTF-8&serverTimezone=JST","root","root");


		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}

	public void DbClose(){
		//接続したものが空でなければ閉じる
		try{
			if(rs != null){
				rs.close();
			}if(con != null){
				con.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}