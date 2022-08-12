package com.coins.ums.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@JsonIgnoreProperties(value = {"handler"})
@TableName("ums_admins")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateAdmin.class, updatePwd.class,updateStatus.class}, message = "ID是大于0的整数")
    private Integer id;

    @NotEmpty(groups = {createAdmin.class, loginData.class}, message = "用户名不能为空")
    private String name;

    @NotEmpty(groups = {updateAdmin.class, createAdmin.class}, message = "真实姓名不能为空")
    private String realname;

    @NotEmpty(groups = {updateAdmin.class, createAdmin.class}, message = "邮箱不能为空")
    @Pattern(regexp = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$", groups = {updateAdmin.class, createAdmin.class}, message = "邮箱格式")
    private String email;

    @NotEmpty(groups = {updatePwd.class, createAdmin.class, loginData.class}, message = "密码不能为空")
    @Length(groups = {updatePwd.class, createAdmin.class, loginData.class}, min = 6, max = 15, message = "密码长度6-15位")
    private String password;

    private String crypt;

    @NotEmpty(groups = {updateAdmin.class, createAdmin.class}, message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", groups = {updateAdmin.class, createAdmin.class}, message = "手机号格式不正确")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lasttime;

    private String lastip;

    @Range(min = 0, max = 1, groups = {updateAdmin.class, createAdmin.class,updateStatus.class})
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    // 取到所有角色
    @TableField(exist = false)
    private List<Role> roles;

    // 取到所有部门
    @TableField(exist = false)
    private List<Department> departments;

    // 重复密码
    @NotNull(groups = {updatePwd.class, createAdmin.class}, message = "重复密码不能为空")
    @Length(groups = {updatePwd.class, createAdmin.class}, min = 6, max = 15, message = "重复密码长度6-15位")
    @TableField(exist = false)
    public String repassword;

    // 角色数组
    @NotNull(groups = {updateAdmin.class, createAdmin.class}, message = "角色不能为空")
    @TableField(exist = false)
    public List<Integer> roleIds;

    // 部门数组
    @NotNull(groups = {updateAdmin.class, createAdmin.class}, message = "部门不能为空")
    @TableField(exist = false)
    public List<Integer> departmentIds;

    // 分组校验
    public interface updateAdmin {
    }

    public interface updatePwd {
    }

    public interface createAdmin {
    }

    public interface updateStatus {
    }

    public interface loginData {
    }


}
