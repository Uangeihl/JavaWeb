package com.jsx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jsx.model.User;
import com.jsx.service.UserService;

@Controller
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/doLogin")
    //public String doLogin(HttpServletRequest request)
    public String doLogin(@RequestParam("username") String userName, @RequestParam("password") String pwd,
                          Map<String,Object> map)
    {
        boolean authorized = userService.isAuthorizedUser(userName, pwd);
        if(authorized)
        {
            List<User> userList = userService.getAll();
            map.put("userList", userList);
            map.put("loginUser", userName);
            return "user/user_list";
        }
        else
        {
            return "login";
        }
    }

    @RequestMapping(value = "/getall")
    public String getAll(Map<String,Object> map)
    {
        List<User> userList = userService.getAll();
        map.put("userList", userList);
        return "user/user_list";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ModelAndView query(@RequestParam("username")String userName)
    {
        User user = new User();
        user.setName(userName);

        List<User> userList = userService.getUserListByCondition(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user/user_list");
        return modelAndView;
    }

    @RequestMapping(value = "/toadd")
    public String toAdd(){
        return "user/user_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user){
        log.info(user.getName()+"------------"+user.getAge());
        userService.add(user);
        return "redirect:/getall";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Integer id, Map<String,Object> map)
    {
        User user = userService.getUserById(id);
        map.put("user", user);
        return "user/user_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user)
    {
        userService.update(user);
        return "redirect:/getall";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value="id") Integer id)
    {
        userService.deleteById(id);
        return "redirect:/getall";
    }

    @RequestMapping("/toUploadPage")
    public String toUploadPage()
    {
        return "upload";
    }


    @RequestMapping("/")
    public String getIndex()
    {
        return "login";
    }

    /**
     * desc:????????????
     * @param multifiles
     * @param request
     * @return
     * @throws Exception
     * date:2017???1???24???
     * author:Tonny Chien
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile[] multifiles, HttpServletRequest request) throws Exception
    {
        for (MultipartFile file : multifiles)
        {
            // ??????MultipartFile[]??????????????????,??????????????????MultipartFile?????????
            if (file.isEmpty())
            {
                log.info("???????????????!");
            }
            else
            {
                // ????????????????????????
                String fileName = file.getOriginalFilename();
                // ?????????????????????????????????????????????
                String imgPath = "d:" + File.separator;
                // ???????????????UUID?????????????????????,?????????????????????
                String path = imgPath + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
                //String path = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
                // ????????????????????????,????????????
                log.info(path);
                // ??????????????????path?????????
                File localFile = new File(path);
                file.transferTo(localFile);
            }
        }
        return "success";
    }

    /**
     * desc:????????????
     * @param fileName
     * @param request
     * @param response
     * @return
     * date:2017???1???24???
     * author:Tonny Chien
     */
    @RequestMapping("/download")
    public String download(String fileName, HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try
        {
            //String path = request.getSession().getServletContext().getRealPath("image") + File.separator;
            String path = "d:"+ File.separator;
            InputStream inputStream = new FileInputStream(new File(path + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0)
            {
                os.write(b, 0, length);
            }

            os.flush();
            // ?????????????????????
            os.close();

            inputStream.close();
        }
        catch (Exception e)
        {
            log.error(e);
        }
        // ????????????????????????????????????????????????????????????
        // java+getOutputStream() has already been called for this response
        return null;
    }

    /**
     * desc:json????????????????????? method??????????????????json???????????????????????????
     * @param id
     * @param type
     * @return
     * date:2017???2???3???
     * author:Tonny Chien
     */
    @RequestMapping(value = "/jsonOper", method = RequestMethod.POST)
    public @ResponseBody User jsonSource(@RequestParam("id")Integer id)
    {
        System.out.println("id: " + id );
        User user = userService.getUserById(id);
        return user;

    }

    @RequestMapping("/search")
    public String tosearchPage()
    {
        return "search";
    }
}


