package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderDao {
    /**
     * 增加供应商
     * @param provider
     * @return
     */
    int addProvider(Provider provider);


    /**
     * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
     * @param proName
     * @return
     */
    List<Provider> getProviderList(@Param("proName")String proName,
                                   @Param("proCode")String proCode);

    /**
     * 通过proId删除Provider
     * @param delId
     * @return
     */
    public int deleteProviderById(String delId);


    /**
     * 通过proId获取Provider
     * @param id
     * @return
     */
    public Provider getProviderById(String id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int modifyProvider(Provider provider);
}
