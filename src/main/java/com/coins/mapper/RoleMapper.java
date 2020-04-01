package com.coins.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import com.coins.entity.Role;


@Mapper
public interface RoleMapper {
	/**
     * 通过名字查询用户信息
     */
    Role findRoleByName(String name);

    /**
     * 查询所有用户信息
     */
    @Select("SELECT * FROM roles")
    List<Role> findAllRoles();

    /**
     * 插入用户信息
     */
    @Insert("INSERT INTO roles(name, age,money) VALUES(#{name}, #{status}")
    void insertRole(@Param("name") String name, @Param("status") Integer status);

    /**
     * 根据 id 更新用户信息
     */
    @Update("UPDATE roles SET name = #{name} WHERE id = #{id}")
    void updateRole(@Param("name") String name,@Param("id") int id);

    /**
     * 根据 id 删除用户信息
     */
    @Delete("DELETE from roles WHERE id = #{id}")
    void deleteRole(@Param("id") int id);
}
