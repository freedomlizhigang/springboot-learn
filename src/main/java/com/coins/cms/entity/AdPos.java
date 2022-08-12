package com.coins.cms.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;

import javax.validation.constraints.*;

/**
 * (AdPos)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:12:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_ad_pos")
public class AdPos implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {update.class},message = "ID是大于0的整数")
    private Integer id;
    //0:PC/1:MOB
    @Max(value = 1,groups = {create.class,update.class},message = "请选择类型")
    @Min(value = 0,groups = {create.class,update.class},message = "请选择类型")
    private Integer isMobile;
    //名称
    @NotNull(groups = {create.class,update.class},message = "名称不能为空")
    @Size(min = 1,max = 255,groups = {create.class,update.class},message = "名称长度不正确")
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

