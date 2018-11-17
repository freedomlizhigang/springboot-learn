package com.springboot.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Role;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
	   * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param username 用户名
     * @return 统计结果
     */
    int countByName(String name);
}
