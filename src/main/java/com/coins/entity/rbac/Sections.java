package com.coins.entity.rbac;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    private Integer id;
    
    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(groups = updateStatus.class,message = "状态必填")
    @Min(value = 1,groups = updateStatus.class)
    @Max(3)
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 分组校验
    public interface updateStatus {
    }
}
