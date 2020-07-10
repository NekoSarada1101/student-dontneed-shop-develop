
package shop.model.service;

import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;
import shop.model.dao.UserDao;

public class UserService extends CommonService {

    private UserDao userDao = new UserDao();

    public boolean checkMemberMailExists(String memberMail) {
        return userDao.checkMemberMailExists(memberMail);
    }

    public boolean insertMember(MemberBeans membeerBeans) {
        return userDao.insertMember(membeerBeans);
    }

    public AdminBeans fetchAdminLogin(String adminMail, String adminPassword) {
        return userDao.fetchAdminLogin(adminMail, adminPassword);
    }

    public boolean deleteMember(MemberBeans memberBeans) {
        return userDao.deleteMember(memberBeans);
    }
}
