package com.coins.entity.rbac;

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
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admins implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer sectionId;

    private String name;

    private String realname;

    private String email;

    private String password;

    private String crypt;

    private String phone;

    private LocalDateTime lasttime;

    private String lastip;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
