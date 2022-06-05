package cn.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserDao {
    /**
     * 增加用户信息
     *
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    public int addUser(User user);

    /**
     * 通过userCode获取User
     *
     * @param userCode
     * @return
     */
    public User getLoginUser(String userCode);

    /**
     * 通过条件查询-userList
     *
     * @param userName
     * @param userRole
     * @return
     * @throws Exception
     */
    public List<User> getUserList(@Param("userName") String userName,
                                  @Param("userRole") int userRole,
                                  @Param("currentPageNo") int currentPageNo,
                                  @Param("pageSize") int pageSize);

    /**
     * 通过条件查询-用户表记录数
     *
     * @param connection
     * @param userName
     * @param userRole
     * @return
     * @throws Exception
     */
    public int getUserCount(@Param("userName") String userName,
                            @Param("userRole") int userRole);

    /**
     * 通过userId删除user
     *
     * @param delId
     * @return
     * @throws Exception
     */
    public int deleteUserById(Integer delId);


    /**
     * 通过userId获取user
     *
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(String id);

    /**
     * 修改用户信息
     *
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    public int modifyUser(User user);


    /**
     * 修改当前用户密码
     *
     * @param connection
     * @param id
     * @param pwd
     * @return
     * @throws Exception
     */
    public int updatePwd(@Param("id") int id,
                         @Param("pwd") String pwd);

}
