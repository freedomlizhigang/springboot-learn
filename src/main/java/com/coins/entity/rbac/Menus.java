package com.coins.entity.rbac;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
    private Integer id;

    private Integer parentid;

    private String arrparentid;

    private Boolean child;

    private String arrchildid;

    private String name;

    private String url;

    private String label;

    private String icon;

    private Integer display;

    private Integer sort;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
