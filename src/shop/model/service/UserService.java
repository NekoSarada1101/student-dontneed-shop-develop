package shop.model.service;

<<<<<<< HEAD
=======
import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;
import shop.model.dao.UserDao;

public class UserService extends CommonService {

<<<<<<< HEAD

	public boolean checkMemberMailExists(String memberMail ) {

		UserDao userDao = new UserDao();

		boolean exists = userDao.checkMemberMailExists(memberMail);

		return exists;

	}





	public boolean insertMember(MemberBeans membeerBeans){

		UserDao userDao = new UserDao();

		boolean couldInsert = userDao.insertMember(membeerBeans);

		return couldInsert;
	}


=======
    private UserDao userDao = new UserDao();

    public AdminBeans fetchAdminLogin(String adminMail, String adminPassword) {
        return userDao.fetchAdminLogin(adminMail, adminPassword);
    }

    public boolean deleteProduct(MemberBeans memberBeans) {
        return userDao.deleteProduct(memberBeans);
    }
}
