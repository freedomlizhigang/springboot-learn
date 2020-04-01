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
public class Logs implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer adminId;

    private String user;

    private String method;

    private String url;

    private String data;

    private LocalDateTime createdAt;


}
