package cn.smbms.service.role;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	/*private SqlSession session = null;

	public RoleServiceImpl(){
		session = MybaitisUtil.getSession();
	}*/

    @Resource
    private RoleDao roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

}
