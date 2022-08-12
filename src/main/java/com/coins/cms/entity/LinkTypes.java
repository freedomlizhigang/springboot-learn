package com.coins.cms.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

/**
 * (LinkTypes)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_link_types")
public class LinkTypes implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {update.class},message = "ID是大于0的整数")
    private Integer id;
    //名称
    @NotNull(groups = {create.class,update.class},message = "名称不能为空")
    private String name;
    //删除状态:1 已删除 ，0 正常
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    public interface create{};
    public interface update{};
}

