package com.coins.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coins.mapper.RoleMapper;
import com.coins.entity.Role;

@Service
public class RoleService {
	@Autowired
    private RoleMapper RoleMapper;


    /**
     * 根据名字查找用户
     */
    public Role selectByName(String name) {
        return RoleMapper.findRoleByName(name);
    }

    /**
     * 查找所有用户
     */
    public List<Role> selectAllUser() {
        return RoleMapper.findAllRoles();
    }

    /**
     * 插入两个用户
     */
    public void insertService() {
    	RoleMapper.insertRole("SnailClimb", 2);
    	RoleMapper.insertRole("Daisy", 1);
    }

    /**
     * 根据id 删除用户
     */

    public void deleteService(int id) {
    	RoleMapper.deleteRole(id);
    }

    /**
     * 模拟事务。由于加上了 @Transactional注解，如果转账中途出了意外 SnailClimb 和 Daisy 的钱都不会改变。
     */
    @Transactional
    public void changemoney() {
    	RoleMapper.updateRole("SnailClimb", 2);
        // 模拟转账过程中可能遇到的意外状况
        int temp = 1 / 0;
        RoleMapper.updateRole("Daisy", 1);
    }
}
