package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.RolePrivs;
import com.coins.mapper.rbac.RolePrivsMapper;
import com.coins.service.rbac.IRolePrivsService;
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
public class RolePrivsServiceImpl extends ServiceImpl<RolePrivsMapper, RolePrivs> implements IRolePrivsService {

}
