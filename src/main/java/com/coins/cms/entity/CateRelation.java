package com.coins.cms.entity;


import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;

/**
 * (CateRelation)表实体类
 *
 * @author makejava
 * @since 2022-08-10 16:15:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@TableName("cms_cate_relation")
public class CateRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    //祖先节点
    private Integer ancestor;
    //子节点
    private Integer descendant;
    //相隔层级，>=1
    private Integer distance;

}

