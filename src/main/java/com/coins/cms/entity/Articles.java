package com.coins.cms.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * (Articles)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_articles")
public class Articles implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {update.class}, message = "ID是大于0的整数")
    private Integer id;
    @PositiveOrZero(groups = {create.class, update.class}, message = "栏目必须填写")
    private Integer cateId;
    @Size(min = 1, max = 255, groups = {create.class, update.class}, message = "标题长度不正确")
    private String title;
    //关键字
    private String keywords;
    //缩略图
    @Size(min = 0, max = 255, groups = {create.class, update.class}, message = "图片地址长度不正确")
    private String thumb;
    //描述信息
    private String description;
    //内容
    @NotNull(groups = {create.class, update.class}, message = "内容不能为空")
    private String content;
    //默认模板文件
    private String tpl;
    //1推荐，0不推荐
    private Integer pushFlag;
    //来源
    @Size(min = 0, max = 255, groups = {create.class, update.class}, message = "来源不正确")
    private String source;
    //是否外部链接：1是，0否
    private Integer linkFlag;
    //链接 URL
    @Size(min = 0, max = 255, groups = {create.class, update.class}, message = "链接地址长度不正确")
    private String url;
    //排序
    @PositiveOrZero(groups = {create.class, update.class}, message = "排序值不正确")
    private Integer sort;
    //点击量
    @PositiveOrZero(groups = {create.class, update.class}, message = "点击量不正确")
    private Integer hits;
    //发布时间
    private LocalDateTime publishAt;
    //1删除，0正常
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    public interface create {
    }

    public interface update {
    }

}

