package cn.smbms.service.user;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;
/**
 * service层捕获异常，进行事务处理
 * 事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）
 * 事务完成之后，需要在service层进行connection的关闭，在dao层关闭（PreparedStatement和ResultSet对象）
 * @author Administrator
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;


    public int add(User user) {
        return userDao.addUser(user);
    }

    public User login(String userCode, String userPassword) {
        User user = userDao.getLoginUser(userCode);
        //匹配密码
        if(null != user){
            if(!user.getUserPassword().equals(userPassword))
                user = null;
        }
        return user;
    }

    @Override
    public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize) {
        return userDao.getUserList(queryUserName,queryUserRole,currentPageNo,pageSize);
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        return userDao.getLoginUser(userCode);
    }

    public int deleteUserById(Integer delId) {
        return userDao.deleteUserById(delId);
    }

    public User getUserById(String id) {
        return  userDao.getUserById(id);
    }

    public int modifyUser(User user) {
        return userDao.modifyUser(user);
    }

    public int updatePwd(int id, String pwd) {
        return userDao.updatePwd(id, pwd);
    }

    public int getUserCount(String userName, int userRole) {
        return  userDao.getUserCount(userName, userRole);
    }
}
