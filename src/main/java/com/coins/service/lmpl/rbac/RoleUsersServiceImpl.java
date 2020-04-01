package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.RoleUsers;
import com.coins.mapper.rbac.RoleUsersMapper;
import com.coins.service.rbac.IRoleUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-01
 */
@Service
public class RoleUsersServiceImpl extends ServiceImpl<RoleUsersMapper, RoleUsers> implements IRoleUsersService {

}
