package com.coins.rbac.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.coins.rbac.entity.Admins;
import com.coins.rbac.entity.Admins.createAdmin;
import com.coins.rbac.entity.Admins.updatePwd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminRequest extends Admins {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	查询用的ID
	@NotNull(groups = showDetail.class,message = "ID必填")
	@Min(value = 1,groups = showDetail.class,message = "ID不能小于1")
	public Integer detailId;
	//	分页大小
	public Integer pageSize = 10;
	//	页码
	public Integer page = 0;
	//	关键字
	public String key;
	
	// 重复密码
	@NotNull(groups = {updatePwd.class,createAdmin.class},message = "重复密码不能为空")
    @Length(groups = {updatePwd.class,createAdmin.class},min=6,max=15,message = "重复密码长度6-15位")
	public String repassword;
	
	// 角色数组
	@NotNull(groups = {updateAdmin.class,createAdmin.class},message = "角色不能为空")
	public List<Integer> roleId;
	
	public interface showDetail{
    }
}
