package com.coins.request.rbac;

import com.coins.entity.rbac.Sections;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SectionListRequest extends Sections {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer pageSize = 0;
	public Integer page = 1;
	public String key;
}
