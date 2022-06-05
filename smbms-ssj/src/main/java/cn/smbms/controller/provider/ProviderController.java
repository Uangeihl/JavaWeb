package cn.smbms.controller.provider;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;
import cn.smbms.tools.UploadUtil;

/**
 * 供应商控制器
 * @author 96114
 *
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    private ProviderService providerService;
    @RequestMapping("/delete")
    public Object delete(String id){
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            int flag = providerService.deleteProviderById(id);
            if(flag == 0){//删除成功
                resultMap.put("delResult", "true");
            }else if(flag == -1){//删除失败
                resultMap.put("delResult", "false");
            }else if(flag > 0){//该供应商下有订单，不能删除，返回订单数
                resultMap.put("delResult", String.valueOf(flag));
            }
        }else{
            resultMap.put("delResult", "notexit");
        }
        return "redirect:/provider/providerList";
    }

    @RequestMapping(value="/view/{id}")
    public String view(@PathVariable("id") String id, Model model){
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider", provider);
        return "providerview";
    }


    @RequestMapping("/toModify/{id}")
    public String toModify (@PathVariable(value="id")String id, Model model) {
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider", provider);
        return "providermodify";
    }

    @RequestMapping("/modify")
    public String modify(@ModelAttribute("provider")Provider provider,HttpSession session) {
        provider.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        int num  = providerService.modifyProvider(provider);
        if(num > 0){
            return "redirect:/provider/providerList";
        }
        return "providermodify";
    }

    @RequestMapping(value="/add.do",method=RequestMethod.GET)
    public String toAdd(@ModelAttribute("provider")Provider provider) {
        return "provideradd";
    }
    @RequestMapping(value="/add.do",method=RequestMethod.POST)
    public String add(@ModelAttribute("provider")Provider provider,
                      @RequestParam(value="pic",required=false)MultipartFile pic,
                      HttpSession session,Model model) throws IllegalStateException, IOException {
        provider.setBusinessLicense(UploadUtil.upload(pic, session));
        System.out.println(provider.getBusinessLicense());
        provider.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreationDate(new Date());
        int num = providerService.addProvider(provider);
        if(num > 0){
            return "redirect:/provider/providerList";
        }
        return "provideradd";
    }

    @RequestMapping(value="/providerList")
    public String providerList(@RequestParam(value="queryProName",required=false,defaultValue="")String queryProName,
                               @RequestParam(value="queryProCode",required=false,defaultValue="")String queryProCode,
                               Model model){
        List<Provider> providerList = new ArrayList<Provider>();
        providerList = providerService.getProviderList(queryProName,queryProCode);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProName", queryProName);
        model.addAttribute("queryProCode", queryProCode);
        return "providerlist";
    }
}
