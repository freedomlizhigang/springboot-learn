package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.Admins;
import com.coins.mapper.rbac.AdminsMapper;
import com.coins.service.rbac.IAdminsService;
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
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements IAdminsService {

}
