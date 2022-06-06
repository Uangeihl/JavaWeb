package Control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDeal {
//    该用户信息是否正确函数
    public boolean isLogin(User user) throws SQLException {
        boolean result = false;
        DBManager db = new DBManager();
        Connection conn = DBManager.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from manager.user where username='" + user.getUserName() + "'and password='" + user.getPassword() + "'";
        ResultSet resultSet=stmt.executeQuery(sql);
        if(resultSet.next()) result = true;
        return result;
    }
//    是否存在该用户函数
    public boolean isUserExist(User user) throws SQLException {
        boolean result = false;
        DBManager db = new DBManager();
        Connection conn = DBManager.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from manager.user where username='" + user.getUserName() + "'";
        if(stmt.executeQuery(sql).next()) result = true;
        return result;
    }
//    是否插入成功函数
    public boolean isInsertSuccess(User user) throws SQLException {
        boolean result = false;
        Connection conn = DBManager.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO manager.user (username,password) values('" + user.getUserName() + "','" + user.getPassword() + "')";
        if(stmt.executeUpdate(sql) >= 1) result = true;
        return result;
    }
}
