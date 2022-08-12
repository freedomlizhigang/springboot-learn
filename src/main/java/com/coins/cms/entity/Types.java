package com.coins.cms.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * (Types)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_types")
public class Types implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateType.class,sortType.class},message = "ID是大于0的整数")
    private Integer id;
    //父 ID
    @PositiveOrZero(groups = {createType.class},message = "父级分类ID输入大于0的整数")
    @NotNull(groups = {createType.class},message = "父级分类ID输入大于0的整数")
    private Integer parentId;
    //菜单名称
    @NotNull(groups = {createType.class,updateType.class},message = "名称必填")
    private String name;
    //排序，正序
    @PositiveOrZero(groups = {createType.class,updateType.class,sortType.class},message = "排序输入大于等于0的整数")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    public interface createType{};
    public interface updateType{};
    public interface sortType{};


}

