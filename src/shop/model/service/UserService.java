package shop.model.service;

import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;
import shop.model.dao.UserDao;

public class UserService extends CommonService {

    private UserDao userDao = new UserDao();

    public MemberBeans fetchMemberLogin(String memberMail, String memberPassword) {
        return userDao.fetchMemberLogin(memberMail, memberPassword);
    }

    public boolean checkMemberMailExists(String memberMail) {
        return userDao.checkMemberMailExists(memberMail);
    }

    public boolean insertMember(MemberBeans memberBeans) {
        return userDao.insertMember(memberBeans);
    }

    public boolean updateMember(MemberBeans memberBeans) {
        return userDao.updateMember(memberBeans);
    }

    public boolean deleteMember(MemberBeans memberBeans) {
        return userDao.deleteMember(memberBeans);
    }

    public AdminBeans fetchAdminLogin(String adminMail, String adminPassword) {
        return userDao.fetchAdminLogin(adminMail, adminPassword);
    }
}
