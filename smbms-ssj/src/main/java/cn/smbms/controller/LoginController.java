package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.smbms.exception.LoginException;
import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {

    @Resource
    private UserService userService ;

	/*@RequestMapping("/*")
	public String returnLogin(HttpSession session){
		if(session.getAttribute(Constants.USER_SESSION) != null ){
			//重定向到登录页面
		}
		return "redirect:/login.html";
	}
	*/

    //跳转到登录页面
    @RequestMapping(value="/frame.html",method=RequestMethod.GET)
    public String toFrame(HttpSession session){
        //先判断是否已经登录
        if(session.getAttribute(Constants.USER_SESSION) == null ){
            //重定向到登录页面
            return "redirect:/login.html";
        }
        return "frame";
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        //重定向到登录页面
        return "redirect:/login.html";
    }


    //跳转到登录页面
    @RequestMapping(value="/login.html",method=RequestMethod.GET)
    public String toLogin(){
        return "login";
    }


    //处理登录请求
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(String userCode,String userPassword,HttpSession session,Model model){
        System.out.println("login ============ " );
        //获取用户名和密码
        //调用service方法，进行用户匹配
        User user = userService.login(userCode,userPassword);
        if(null != user){//登录成功
            //放入session
            session.setAttribute(Constants.USER_SESSION, user);
            //页面跳转（frame.jsp）
            return "redirect:frame.html";
        }
        throw new LoginException();
    }
}
