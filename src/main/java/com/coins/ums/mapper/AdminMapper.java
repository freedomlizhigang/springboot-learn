package com.coins.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coins.ums.entity.Admin;
import com.coins.ums.entity.Role;
import com.coins.ums.request.AdminRequest;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    // 根据id获取单个用户信息及角色、部门
    public Map<String,Object> getDetailAndRoleDepartmentIds(Integer id);
    // 根据id获取单个用户信息及角色、部门
    public Map<String,Object> getDetailAndRoleDepartment(Integer id);

    // 获取用户列表，带角色、部门信息
    public List<Admin> getListAndRoleDepartment(AdminRequest adminRequest);

    // 注解功能测试，没有用
    @Select("select * from ums_admins")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "realname", column = "realname"),
        @Result(property = "roles", column = "id", javaType = List.class,
            many = @Many(select = "com.coins.ums.mapper.AdminMapper.findRoleByUid"))
    })
    public List<Admin> findAllUserAndRole();

    //roleMapper 内
    @Select("select * from ums_roles r, ums_role_admins ua where r.id = ua.role_id and ua.admin_id = #{uid}")
    public List<Role> findRoleByUid(Integer uid);
}
