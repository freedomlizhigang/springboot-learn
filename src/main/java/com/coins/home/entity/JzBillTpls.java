package com.coins.home.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * (JzBillTpls)表实体类
 *
 * @author makejava
 * @since 2022-07-12 17:28:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JzBillTpls implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;
    //运营商ID
    private BigInteger operatorId;
    //名称
    private String name;
    //分时段费率
    private String group;
    //折扣
    private Integer discount;
    //状态，1 正常，0 禁用
    private Integer status;
    //删除状态，1 删除，0 未删除
    private Integer delFlag;
    //创建时间
    private LocalDateTime createdAt;
    //更新时间
    private LocalDateTime updatedAt;

}

