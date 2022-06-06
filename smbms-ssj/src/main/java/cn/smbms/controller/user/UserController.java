package cn.smbms.controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import cn.smbms.tools.UploadUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping("/getUserJsonData")
    public String getUserJsonData(String id,Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userviewajax";
    }

	/*@RequestMapping("/getUserJsonData")
	public @ResponseBody Object getUserJsonData(String id) {
		User user = userService.getUserById(id);
		//转换为json字符串
		//String jsonString = JSON.toJSONString(user);
		//在配置文件中配置了FastJsonHttpMessageConvert之后，就不要手动转换了
		//String jsonString = JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd");
		//返回josn字符串
		//返回对象后，FastJsonHttpMessageConvert会自动把返回的对象转为json字符串
		return user;
	}*/


    //判断是否重名
    @RequestMapping("/userCodeIsExist")
    public @ResponseBody Object userCodeIsExist(String userCode) {
        User user = userService.selectUserCodeExist(userCode);
        Map<String,String> resultMap = new HashMap<String, String>();
        resultMap.put("userCode", user==null?"":"exist");
        return resultMap;
    }


    @RequestMapping("/toPwdUpdate")
    public String toPwdUpdate() {
        return "pwdmodify";
    }

    @RequestMapping("/pwdUpdate")
    public String pwdUpdate(String newpassword,HttpSession session,Model model){
        Object o = session.getAttribute(Constants.USER_SESSION);
        if(o != null && !StringUtils.isNullOrEmpty(newpassword)){
            int num = userService.updatePwd(((User)o).getId(),newpassword);
            if(num > 0){
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                session.removeAttribute(Constants.USER_SESSION);//session注销
            }else{
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        return "pwdmodify";
    }

    @RequestMapping("/pwdIsRight")
    public @ResponseBody Object pwdIsRight(String oldpassword,HttpSession session, Model model) {
        Object o = session.getAttribute(Constants.USER_SESSION);
        Map<String, String> resultMap = new HashMap<String, String>();
        if(null == o ){//session过期
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//旧密码输入为空
            resultMap.put("result", "error");
        }else{
            String sessionPwd = ((User)o).getUserPassword();
            System.out.println();
            if(oldpassword.equals(sessionPwd)){
                resultMap.put("result", "true");
            }else{//旧密码输入不正确
                resultMap.put("result", "false");
            }
        }
        return resultMap;
    }


    @RequestMapping("/delete")
    public @ResponseBody Object delete(Integer id) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(id <= 0){
            resultMap.put("delResult", "notexist");
        }else{
            int num = userService.deleteUserById(id);
            if(num > 0){
                resultMap.put("delResult", "true");
            }else{
                resultMap.put("delResult", "false");
            }
        }
        return resultMap;
    }

    @RequestMapping("/tomodify")
    public String getUserById(String id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "usermodify";
    }
    @RequestMapping("/modify")
    public String modify(@ModelAttribute("user")User user, Integer uid ,HttpSession session) {
        user.setId(uid);
        user.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());
        if (userService.modifyUser(user)>0) {
            return "redirect:/user/userlist";
        }
        return "usermodify";
    }

    @ModelAttribute("roleList")
    public List<Role> getRoleList() {
        List<Role> roleList = null;
        roleList = roleService.getRoleList();
        return roleList;
    }

    //查看用户详细信息
    @RequestMapping("/view/{id}")
    public String userview(@PathVariable(value="id")String id,Model model) {
        //调用后台方法得到user对象
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userview";
    }

    @RequestMapping(value="/add.do",method=RequestMethod.GET)
    public String toAdd(@ModelAttribute("user")User user) {
        return "useradd";
    }

    @RequestMapping(value="/add.do",method=RequestMethod.POST)
    public String doAdd(@Valid @ModelAttribute("user")User user, HttpSession session,
                        @RequestParam(value="pic",required=false)MultipartFile pic,
                        @RequestParam(value="workPic",required=false)MultipartFile workPic) throws IllegalStateException, IOException {
        user.setIdPicPath(UploadUtil.upload(pic, session));
        user.setWorkPicPath(UploadUtil.upload(workPic, session));
        user.setCreationDate(new Date());
        user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        if(userService.add(user) > 0){
            return "redirect:/user/userlist";
        }
        return "useradd";
    }

    @RequestMapping("/userlist")
    public String userList(@RequestParam(value="queryname",required=false,defaultValue="")String queryUserName,
                           @RequestParam(value="queryUserRole",required=false,defaultValue="0")Integer queryUserRole,
                           @RequestParam(value="pageIndex",required=false,defaultValue="1")Integer currentPageNo,
                           Model model) {
        //查询用户列表
        List<User> userList = null;
        //设置页面容量
        int pageSize = Constants.pageSize;

        //总数量（表）
        int totalCount	= userService.getUserCount(queryUserName,queryUserRole);
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        userList = userService.getUserList(queryUserName,queryUserRole,(currentPageNo-1)*pageSize, pageSize);
        model.addAttribute("userList", userList);
        //		List<Role> roleList = null;
        //		roleList = roleService.getRoleList();
        //		model.addAttribute("roleList", roleList);

        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "userlist";
    }
}
