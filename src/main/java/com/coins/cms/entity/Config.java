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

/**
 * (Config)表实体类
 *
 * @author makejava
 * @since 2022-08-02 09:13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_config")
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateConfig.class},message = "ID是大于0的整数")
    private Integer id;
    //站点名称
    @NotNull(groups = {updateConfig.class},message = "名称不能为空")
    private String sitename;
    //描述
    private String description;
    //联系人
    private String person;
    //联系电话
    private String phone;
    //邮箱
    private String email;
    //地址
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    public interface updateConfig{};
}

