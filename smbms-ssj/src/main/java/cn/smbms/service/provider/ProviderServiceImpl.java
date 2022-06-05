package cn.smbms.service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderDao providerMapper ;

    @Resource
    private BillDao billMapper;

    @Override
    public int addProvider(Provider provider) {
        int num = providerMapper.addProvider(provider);
        /*session.commit();*/
        return num;
    }

    @Override
    public List<Provider> getProviderList(String proName,String proCode) {
        return providerMapper.getProviderList(proName, proCode);
    }

    /**
     * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
     * 若订单表中无该供应商的订单数据，则可以删除
     * 若有该供应商的订单数据，则不可以删除
     * 返回值billCount
     * 1> billCount == 0  删除---1 成功 （0） 2 不成功 （-1）
     * 2> billCount > 0    不能删除 查询成功（0）查询不成功（-1）
     * ---判断
     * 如果billCount = -1 失败
     * 若billCount >= 0 成功
     */
    @Override
    public int deleteProviderById(String delId) {
        int billCount = -1;
        try {
            billCount = billMapper.getBillCountByProviderId(delId);
            if(billCount == 0){
                billCount = providerMapper.deleteProviderById(delId);
            }
        } catch (Exception e) {
            billCount = -1;
            throw new RuntimeException();
        }
        return billCount;
    }

    @Override
    public Provider getProviderById(String id) {
        return providerMapper.getProviderById(id);
    }

    @Override
    public int modifyProvider(Provider provider) {
        int num = providerMapper.modifyProvider(provider);
        /*session.commit();*/
        return num;
    }
}
