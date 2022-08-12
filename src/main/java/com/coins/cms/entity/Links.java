package com.coins.cms.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * (Links)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_links")
public class Links implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {update.class,sort.class},message = "ID是大于0的整数")
    private Integer id;
    //分类ID
    @PositiveOrZero(groups = {create.class,update.class},message = "分类必须填写")
    private Integer linktypeId;
    //标题
    @NotNull(groups = {create.class,update.class},message = "名称不能为空")
    @Size(min = 1,max = 255,groups = {create.class,update.class},message = "名称长度不正确")
    private String title;
    //图片
    @Size(min = 0,max = 255,groups = {create.class,update.class},message = "图片地址长度不正确")
    private String thumb;
    //链接
    @URL(groups = {create.class,update.class},message = "链接地址不正确")
    private String url;
    //排序
    @PositiveOrZero(groups = {create.class,update.class,sort.class},message = "排序值不正确")
    private Integer sort;
    //状态，1正常0关闭
    private Integer status;
    //删除状态:1 已删除 ，0 正常
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    public interface create{};
    public interface update{};
    public interface sort{};

}

