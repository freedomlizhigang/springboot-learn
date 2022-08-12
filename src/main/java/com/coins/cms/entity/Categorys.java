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

import javax.validation.constraints.*;

/**
 * (Categorys)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_categorys")
public class Categorys implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {update.class, sort.class}, message = "ID是大于0的整数")
    private Integer id;
    // 父级id
    @TableField(exist = false)
    @PositiveOrZero(groups = {create.class, update.class}, message = "父级必须填写")
    private Integer parentId;
    //栏目名称
    @Size(min = 1, max = 255, groups = {create.class, update.class}, message = "名称长度不正确")
    private String name;
    //缩略图
    @Size(min = 0, max = 255, groups = {create.class, update.class}, message = "图片地址长度不正确")
    private String thumb;
    //标题
    @Size(min = 1, max = 255, groups = {create.class, update.class}, message = "标题长度不正确")
    private String title;
    //关键字
    private String keyword;
    //描述
    private String description;
    //内容
    @NotNull(groups = {create.class, update.class}, message = "内容不能为空")
    private String content;
    //是否外部链接：1是，0否
    private Integer linkFlag;
    //链接 URL
    @Size(min = 0, max = 255, groups = {create.class, update.class}, message = "链接地址长度不正确")
    private String url;
    //栏目显示模板，列表 list，单页 page
    private String cateTpl;
    //下属文章模板
    private String artTpl;
    //1显示，0不显示
    private Integer display;
    //类型，0 列表，1 单页
    private Integer type;
    //排序
    @PositiveOrZero(groups = {create.class, update.class, sort.class}, message = "排序值不正确")
    private Integer sort;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    public interface create {
    }

    public interface update {
    }

    public interface sort {
    }
}

