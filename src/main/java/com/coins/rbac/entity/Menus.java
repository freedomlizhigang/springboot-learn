package com.coins.rbac.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

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
public class Menus implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {updateMenu.class,detailMenu.class},message = "ID必填")
    private Integer id;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "父级必填")
    private Integer parentid;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "名称必填")
    private String name;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "URL必填")
    private String url;
    
    @NotNull(groups = {createMenu.class,updateMenu.class},message = "权限必填")
    private String label;

    private String icon;
    
    @Range(min=0,max=1,groups= {createMenu.class,updateMenu.class},message="是否显示")
    private Integer display;

    @NotNull(groups = {createMenu.class,updateMenu.class},message = "排序必填")
    private Integer sort;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    // 排除字段，生成树形菜单时用到的
    @TableField(exist = false)
	private List<Menus> children = new ArrayList<Menus>();
    // 分组校验
    public interface createMenu{}
    public interface updateMenu{}
    public interface detailMenu{}
}
