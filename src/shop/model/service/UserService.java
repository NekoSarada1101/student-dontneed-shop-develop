package shop.model.service;

import shop.model.bean.AdminBeans;
import shop.model.dao.UserDao;

public class UserService extends CommonService {

	public AdminBeans fetchAdminLogin(String adminMail,String adminPassword) {

		//戻り値を初期化
		AdminBeans adminBeans = null;
		shop.model.dao.UserDao userDao = new UserDao();

		adminBeans = new AdminBeans();

		adminBeans = userDao.fetchAdminLogin(adminMail,adminPassword);

		return adminBeans;
	}

}
