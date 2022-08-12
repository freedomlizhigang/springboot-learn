package com.coins.ums.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("ums_menus")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Positive(groups = {updateMenu.class,detailMenu.class,sortMenu.class},message = "ID是大于0的整数")
    private Integer id;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "父级必填")
    private Integer parentId;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "名称必填")
    private String name;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "URL必填")
    private String url;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "权限必填")
    private String label;

    private String icon;
    
    @Range(min=0,max=1,groups= {createMenu.class,updateMenu.class},message="是否显示")
    private Integer display;

    @NotNull(groups = {createMenu.class,updateMenu.class,sortMenu.class},message = "排序必填")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    // 排除字段，生成树形菜单时用到的
    @TableField(exist = false)
	private List<Menu> children = new ArrayList<Menu>();
    @TableField(exist = false)
	private Boolean _showChildren = true;
    // 分组校验
    public interface createMenu{}
    public interface updateMenu{}
    public interface detailMenu{}
    public interface sortMenu{}
}
