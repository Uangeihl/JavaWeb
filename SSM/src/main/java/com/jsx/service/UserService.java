package com.jsx.service;
import java.util.List;
import com.jsx.model.User;
public interface UserService {
    //查询user
    public User getUserById(int id);
    public int update(User user);
    public List<User> getAll();
    public List<User> getUserListByCondition(User user);
    public int deleteById(int id);
    public int add(User user);
    /**
     * desc:查询用户名和密码是否正确
     * authour: Tonny Chien 2017年1月24日
     * @param userName
     * @param pwd
     * @return
     */
    public boolean isAuthorizedUser(String userName, String pwd);
}
