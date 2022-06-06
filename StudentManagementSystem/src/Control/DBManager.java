package Control;

import java.sql.*;
//数据库管理程序
public class DBManager {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String pwd = "123456";
    private static Connection conn = null;
//    注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    建立连接
    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, pwd);
            return conn;
        }
        return conn;
    }
}
