package com.coins.entity.rbac;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.coins.request.rbac.SectionListRequest.showDetail;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

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
public class Sections implements Serializable {

	private static final long serialVersionUID=1L;

	@TableId(value = "id", type = IdType.AUTO)
	@NotNull(groups = {updateName.class,updateStatus.class},message = "ID必填")
    private Integer id;
    
    @NotNull(groups = {createSection.class,updateName.class},message = "名称不能为空")
    @Null(groups = updateStatus.class,message = "名称不传")
    private String name;

    @Null(groups = {updateName.class},message = "状态不传")
    @NotNull(groups = {updateStatus.class,createSection.class},message = "状态必填")
    @Min(value = 1,groups = {updateStatus.class,createSection.class})
    @Max(value = 3,groups = {updateStatus.class,createSection.class})
    private Integer status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    // 分组校验
    public interface updateName {
    }
    public interface updateStatus {
    }
    public interface createSection {
    }
}
