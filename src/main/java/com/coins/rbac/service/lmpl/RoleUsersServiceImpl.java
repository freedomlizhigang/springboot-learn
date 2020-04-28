package com.coins.rbac.service.lmpl;

import com.coins.rbac.entity.RoleUsers;
import com.coins.rbac.mapper.RoleUsersMapper;
import com.coins.rbac.service.IRoleUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class RoleUsersServiceImpl extends ServiceImpl<RoleUsersMapper, RoleUsers> implements IRoleUsersService {

}
