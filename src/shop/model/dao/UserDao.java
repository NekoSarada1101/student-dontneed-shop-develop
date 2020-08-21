package shop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.bean.AdminBeans;
import shop.model.bean.MemberBeans;
import shop.model.service.ErrorCheckService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase {

    private Logger logger = LogManager.getLogger();

    public MemberBeans fetchMemberLogin(String memberMail, String password) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt        = null;
        ResultSet         rs          = null;
        MemberBeans       memberBeans;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM member WHERE member_mail = ? AND member_password = ?");
            stmt.setString(1, memberMail);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            rs.next();

            memberBeans = new MemberBeans();
            memberBeans.setMemberMail(rs.getString("member_mail"));
            memberBeans.setMemberPassword(rs.getString("member_password"));
            memberBeans.setMemberName(rs.getString("member_name"));
            memberBeans.setPostalCode(rs.getString("postal_code"));
            memberBeans.setAddress(rs.getString("address"));
            memberBeans.setTell(rs.getString("tell"));
            memberBeans.setCreditCard(rs.getString("credit_card"));
            memberBeans.setExpirationDate(rs.getString("expiration_date"));
            memberBeans.setHolder(rs.getString("holder"));
            memberBeans.setSecurityCode(rs.getString("security_code"));
            logger.info("memberBeans={}", memberBeans);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
            return null;
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return memberBeans;
    }


    public boolean checkMemberMailExists(String memberMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt     = null;
        ResultSet         rs       = null;
        boolean           isExists = false;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM member WHERE exists(SELECT * FROM member m WHERE m.member_mail = ?)");
            stmt.setString(1, memberMail);
            rs = stmt.executeQuery();
            isExists = rs.next();
            logger.info("isExists={}", isExists);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return isExists;
    }


    public boolean insertMember(MemberBeans memberBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("INSERT INTO member (member_mail, member_password, member_name, postal_code, address, tell, credit_card, expiration_date, holder, security_code) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, memberBeans.getMemberMail());
            stmt.setString(2, memberBeans.getMemberPassword());
            stmt.setString(3, memberBeans.getMemberName());
            stmt.setString(4, memberBeans.getPostalCode());
            stmt.setString(5, memberBeans.getAddress());
            stmt.setString(6, memberBeans.getTell());
            stmt.setString(7, memberBeans.getCreditCard());
            stmt.setString(8, memberBeans.getExpirationDate());
            stmt.setString(9, memberBeans.getHolder());
            stmt.setString(10, memberBeans.getSecurityCode());
            insertLine = stmt.executeUpdate();
            logger.info("insertLine={}", insertLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return insertLine != 0;
    }


    public boolean updateMember(MemberBeans memberBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               updateLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE member SET member_mail = ?, member_password = ?, member_name = ?, postal_code = ?, address = ?, tell = ?, credit_card = ?, expiration_date = ?, holder = ?, security_code = ? WHERE member_mail = ?");
            stmt.setString(1, memberBeans.getMemberMail());
            stmt.setString(2, memberBeans.getMemberPassword());
            stmt.setString(3, memberBeans.getMemberName());
            stmt.setString(4, memberBeans.getPostalCode());
            stmt.setString(5, memberBeans.getAddress());
            stmt.setString(6, memberBeans.getTell());
            stmt.setString(7, memberBeans.getCreditCard());
            stmt.setString(8, memberBeans.getExpirationDate());
            stmt.setString(9, memberBeans.getHolder());
            stmt.setString(10, memberBeans.getSecurityCode());
            stmt.setString(11, memberBeans.getMemberMail());
            updateLine = stmt.executeUpdate();
            logger.info("updateList={}", updateLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return updateLine != 0;
    }


    public boolean deleteMember(MemberBeans memberBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("DELETE FROM member WHERE member_mail = ?");
            stmt.setString(1, memberBeans.getMemberMail());
            deleteLine = stmt.executeUpdate();
            logger.info("deleteList={}", deleteLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return deleteLine != 0;
    }


    public AdminBeans fetchAdminLogin(String adminMail, String password) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        ResultSet         rs         = null;
        AdminBeans        adminBeans;

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
            adminBeans.setPostalCode(rs.getString("postal_code"));
            adminBeans.setAddress(rs.getString("address"));
            logger.info("adminBeans={}", adminBeans);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
            return null;
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return adminBeans;
    }


    public boolean checkAdminMailExists(String adminMail) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt     = null;
        ResultSet         rs       = null;
        boolean           isExists = false;

        try {
            this.connect();
            stmt = con.prepareStatement("SELECT * FROM admin WHERE exists(SELECT * FROM admin m WHERE m.admin_mail = ?)");
            stmt.setString(1, adminMail);
            rs = stmt.executeQuery();
            isExists = rs.next();
            logger.info("isExists={}", isExists);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return isExists;
    }


    public boolean insertAdmin(AdminBeans adminBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               insertLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("INSERT INTO admin (admin_mail, admin_password, admin_name, postal_code, address) VALUES (?,?,?,?,?)");
            stmt.setString(1, adminBeans.getAdminMail());
            stmt.setString(2, adminBeans.getAdminPassword());
            stmt.setString(3, adminBeans.getAdminName());
            stmt.setString(4, adminBeans.getPostalCode());
            stmt.setString(5, adminBeans.getAddress());
            insertLine = stmt.executeUpdate();
            logger.info("insertLine={}", insertLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return insertLine != 0;
    }


    public boolean updateAdmin(AdminBeans adminBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               updateLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("UPDATE admin SET admin_mail = ?, admin_password = ?, admin_name = ?, postal_code = ?, address = ? WHERE admin_mail = ?");
            stmt.setString(1, adminBeans.getAdminMail());
            stmt.setString(2, adminBeans.getAdminPassword());
            stmt.setString(3, adminBeans.getAdminName());
            stmt.setString(4, adminBeans.getPostalCode());
            stmt.setString(5, adminBeans.getAddress());
            stmt.setString(6, adminBeans.getAdminMail());
            updateLine = stmt.executeUpdate();
            logger.info("updateList={}", updateLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return updateLine != 0;
    }


    public boolean deleteAdmin(AdminBeans adminBeans) {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        PreparedStatement stmt       = null;
        int               deleteLine = 0;

        try {
            this.connect();
            stmt = con.prepareStatement("DELETE FROM admin WHERE admin_mail = ?");
            stmt.setString(1, adminBeans.getAdminMail());
            deleteLine = stmt.executeUpdate();
            logger.info("deleteList={}", deleteLine);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error", e);
        } finally {
            try {
                this.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
        return deleteLine != 0;
    }
}

