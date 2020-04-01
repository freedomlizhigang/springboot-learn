package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.Roles;
import com.coins.mapper.rbac.RolesMapper;
import com.coins.service.rbac.IRolesService;
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
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

}
