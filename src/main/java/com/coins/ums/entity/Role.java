package com.coins.ums.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
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
@TableName("ums_roles")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateName.class,updateStatus.class},message = "ID是大于0的整数")
    private Integer id;

    @NotNull(groups = {createRole.class,updateName.class},message = "名称不能为空")
    @Null(groups = updateStatus.class,message = "名称不传")
    private String name;

    @NotNull(groups = {updateName.class,updateStatus.class,createRole.class},message = "状态必填")
    @Range(min=0,max=1,groups= {updateName.class,updateStatus.class,createRole.class})
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    // 分组校验
    public interface updateName {}
    public interface updateStatus {}
    public interface createRole {}
}
