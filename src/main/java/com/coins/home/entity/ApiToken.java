package com.coins.home.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ApiToken {
    private static final long serialVersionUID=1L;
    public String name;
    public Integer age;
    public Map<String,String> priv;
    public List<String> label;
}
