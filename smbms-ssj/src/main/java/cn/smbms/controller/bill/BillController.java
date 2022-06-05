package cn.smbms.controller.bill;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;

/**
 * 订单管理控制器
 * @author 96114
 *
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/delete")
    public Object delete(String id) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            int num = billService.deleteBillById(id);
            if(num > 0){//删除成功
                resultMap.put("delResult", "true");
            }else{//删除失败
                resultMap.put("delResult", "false");
            }
        }else{
            resultMap.put("delResult", "notexit");
        }
        //把resultMap转换成json对象输出
        return resultMap;
    }




    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String toAdd() {
        return "billadd";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(Bill bill,HttpSession session) {
        bill.setProductCount(bill.getProductCount().setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(bill.getTotalPrice().setScale(2,BigDecimal.ROUND_DOWN));
        bill.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        int num = billService.add(bill);
        if(num > 0){
            return "redirect:/bill/billList";
        }
        return "billadd";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable(value="id")String id,Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "billview";
    }

    @RequestMapping("/toModify/{id}")
    public String toModify(@PathVariable(value="id") String id,Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "billmodify";
    }

    @RequestMapping("/modify")
    public String modify(@ModelAttribute("bill")Bill bill,HttpSession session) {
        bill.setProductCount(bill.getProductCount().setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(bill.getTotalPrice().setScale(2,BigDecimal.ROUND_DOWN));
        bill.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        int num = billService.modify(bill);
        if(num > 0){
            return "redirect:/bill/billList";
        }
        return "billmodify";
    }


    @ModelAttribute("providerList")
    public List<Provider> getProviderList(Model model) {
        List<Provider> providerList = providerService.getProviderList("","");
        return providerList;
    }


    @RequestMapping(value="/billList")
    public String billList(@RequestParam(value="queryProductName",required=false)String queryProductName,
                           @RequestParam(value="queryProviderId",required=false)Integer queryProviderId,
                           @RequestParam(value="queryIsPayment",required=false)Integer queryIsPayment,
                           Model model){
        //		List<Provider> providerList = new ArrayList<Provider>();
        //		providerList = providerService.getProviderList("","");
        //		model.addAttribute("providerList", providerList);
        List<Bill> billList = new ArrayList<Bill>();
        Bill bill = new Bill();
        bill.setProviderId(queryProviderId);
        bill.setIsPayment(queryIsPayment);
        bill.setProductName(queryProductName);
        billList = billService.getBillList(bill);
        model.addAttribute("billList", billList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);
        return "billlist";
    }
}
