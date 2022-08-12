package com.coins.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coins.ums.entity.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
public interface MenuMapper extends BaseMapper<Menu> {
    // 获取所有父级菜单
    public List<Menu> getAllParent(int id);
}
