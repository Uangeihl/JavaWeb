package cn.smbms.service.bill;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Resource
    private BillDao billDao;

    @Override
    public int add(Bill bill) {
        return billDao.addBill(bill);
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        return billDao.getBillList(bill);
    }

    @Override
    public int deleteBillById(String delId) {
        return billDao.deleteBillById(delId);
    }

    @Override
    public Bill getBillById(String id) {
        return billDao.getBillById(id);
    }

    @Override
    public int modify(Bill bill) {
        return billDao.modifyBill(bill);
    }

    public int getBillCountByProviderId(String providerId){
        return billDao.getBillCountByProviderId(providerId);
    }
}
