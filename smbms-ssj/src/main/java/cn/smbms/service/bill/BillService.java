package cn.smbms.service.bill;

import java.util.List;

import cn.smbms.pojo.Bill;

public interface BillService {
    /**
     * 增加订单
     * @param bill
     * @return
     */
    int add(Bill bill);


    /**
     * 通过条件获取订单列表-模糊查询-billList
     * @param bill
     * @return
     */
    List<Bill> getBillList(Bill bill);

    /**
     * 通过billId删除Bill
     * @param delId
     * @return
     */
    int deleteBillById(String delId);


    /**
     * 通过billId获取Bill
     * @param id
     * @return
     */
    Bill getBillById(String id);

    /**
     * 修改订单信息
     * @param bill
     * @return
     */
    int modify(Bill bill);

    /**
     * 根据供应商ID查询订单数量
     * @param providerId
     * @return
     */
    int getBillCountByProviderId(String providerId);

}
