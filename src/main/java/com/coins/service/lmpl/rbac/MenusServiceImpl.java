package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.Menus;
import com.coins.mapper.rbac.MenusMapper;
import com.coins.service.rbac.IMenusService;
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
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus> implements IMenusService {

}
