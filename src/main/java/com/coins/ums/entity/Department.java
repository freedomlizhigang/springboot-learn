package com.coins.ums.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@TableName("ums_departments")
public class Department implements Serializable {

	private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateDepartment.class, detailDepartment.class,statusDepartment.class},message = "ID是大于0的整数")
    private Integer id;

    @NotNull(groups = {createDepartment.class, updateDepartment.class},message = "父级必填")
    private Integer parentId;

    @NotNull(groups = {createDepartment.class, updateDepartment.class},message = "名称必填")
    private String name;

    @Range(min=0,max=1,groups= {updateDepartment.class, createDepartment.class,statusDepartment.class},message = "状态必填")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    // 分组校验
    public interface createDepartment{}
    public interface updateDepartment{}
    public interface detailDepartment{}
    public interface statusDepartment{}
}
