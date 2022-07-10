package com.coins.rbac.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.baomidou.mybatisplus.annotation.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coins.rbac.mapper.RoleUsersMapper;
import com.coins.rbac.mapper.RolesMapper;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admins implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {updateAdmin.class,updatePwd.class},message = "ID必填")
    private Integer id;
    
    @NotNull(groups = {updateAdmin.class,createAdmin.class},message = "部门不能为空")
    private Integer sectionId;
    
    @NotEmpty(groups = {createAdmin.class,loginData.class},message = "用户名不能为空")
    @Null(groups = {updateAdmin.class,updatePwd.class,loginData.class},message = "用户名未填写")
    private String name;
    
    @NotNull(groups = {updateAdmin.class,createAdmin.class},message = "真实姓名不能为空")
    private String realname;
    
    @NotNull(groups = {updateAdmin.class,createAdmin.class},message = "邮箱不能为空")
    @Pattern(regexp = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$",groups = {updateAdmin.class,createAdmin.class},message = "邮箱格式")
    private String email;
    
    @NotNull(groups = {updatePwd.class,createAdmin.class,loginData.class},message = "密码不能为空")
    @Length(groups = {updatePwd.class,createAdmin.class,loginData.class},min=6,max=15,message = "密码长度6-15位")
    @Null(groups = {updateAdmin.class,loginData.class},message = "密码不传")
    private String password;

    private String crypt;
    
    @NotNull(groups = {updateAdmin.class,createAdmin.class},message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",groups = {updateAdmin.class,createAdmin.class},message = "手机号格式不正确")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lasttime;

    private String lastip;
    
    @Range(min=0,max=1,groups= {updateAdmin.class,createAdmin.class})
    private Integer status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    // 分组校验
    public interface updateAdmin {
    }
    public interface updatePwd {
    }
    public interface createAdmin {
    }
    public interface loginData{}
    
    
    // 取到所有角色
//    private List<Roles> roles;
    // 用户角色关联
 	@Autowired
 	private RoleUsersMapper roleUserMapper;
 	@Autowired
 	private RolesMapper roleMapper;
	public List<Roles> getRoles()
	{
	 	QueryWrapper<RoleUsers> queryWrapper = new QueryWrapper<>();
	 	queryWrapper.eq("user_id",this.getId()).select("role_id");
	 	List<RoleUsers> role_users = roleUserMapper.selectList(queryWrapper);
	 	QueryWrapper<Roles> queryRoles = new QueryWrapper<>();
	 	queryRoles.in("id",role_users);
	 	List<Roles> roles = roleMapper.selectList(queryRoles);
	 	return roles;
	}
}
