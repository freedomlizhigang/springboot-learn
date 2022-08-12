package com.coins.ums.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * (UmsDepartmentAdmins)表实体类
 *
 * @author makejava
 * @since 2022-07-19 17:26:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ums_department_admins")
public class DepartmentAdmin implements Serializable {

    private static final long serialVersionUID=1L;
    @NotNull(groups = {createEntity.class},message = "部门ID必填")
    private Integer departmentId;
    @NotNull(groups = {createEntity.class},message = "用户ID必填")
    private Integer adminId;
    public interface createEntity{};
}

