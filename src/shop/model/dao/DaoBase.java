
package shop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.model.service.ErrorCheckService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
    protected Connection con    = null;
    private   Logger     logger = LogManager.getLogger();

    public void connect() {
        logger.trace("{} Start", ErrorCheckService.getMethodName());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_dontneed_shop?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false", "root", "root");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("error={}", e);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error={}", e);
        } finally {
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
    }

    public void close() {
        logger.trace("{} Start", ErrorCheckService.getMethodName());
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error={}", e);
        } finally {
            logger.trace("{} End", ErrorCheckService.getMethodName());
        }
    }
}
