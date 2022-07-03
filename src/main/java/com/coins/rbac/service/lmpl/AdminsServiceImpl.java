package com.coins.rbac.service.lmpl;

import com.coins.rbac.entity.Admins;
import com.coins.rbac.entity.RoleUsers;
import com.coins.rbac.mapper.AdminsMapper;
import com.coins.rbac.mapper.RoleUsersMapper;
import com.coins.rbac.request.AdminRequest;
import com.coins.rbac.service.IAdminsService;
import com.coins.rbac.service.IRoleUsersService;
import com.coins.utils.ResultUtil;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements IAdminsService {
	// 管理员
	@Autowired
	private AdminsMapper adminMapper;
	// 用户角色关联
	@Autowired
	private RoleUsersMapper roleUserMapper;
	@Autowired
	private IRoleUsersService roleuserService;
	@Autowired
	private HttpServletRequest request;
	//	登录
	public Admins loginData(AdminRequest admin) {
		QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",admin.detailId);
		Admins detail = adminMapper.selectOne(queryWrapper);
		return detail;
	}
	// 获取列表
	public Map<String, Object> getList(AdminRequest adminList) {
		QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().like(adminList.key != null,Admins::getName, adminList.key).last("limit " + adminList.page * adminList.pageSize + "," + adminList.pageSize);
		List<Admins> all = adminMapper.selectList(queryWrapper);
		Integer count = adminMapper.selectCount(queryWrapper);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", all);
		return map;
	}
	//	获取单条
	public Admins getDetail(AdminRequest admin) {
		QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",admin.detailId);
		Admins detail = adminMapper.selectOne(queryWrapper);
		return detail;
	}
	//	插入单条
	@Transactional
	public Object create(AdminRequest admin) throws Exception {
		// 密码是否相等
		String pwd = admin.getPassword();
		String pwd2 = admin.getRepassword();
		if (!pwd.equals(pwd2)) {
			return ResultUtil.error(400, "两次密码不同");
		}
		// 加密，秘钥
		String crypt = RandomUtil.randomString(10);
		admin.setCrypt(crypt);
		String pwd3 = SecureUtil.md5(SecureUtil.md5(admin.getPassword() + "." + crypt + "." + admin.getPassword()));
		admin.setPassword(pwd3);
		// 取ip
		String[] headername = {};
		String ip = ServletUtil.getClientIP(request, headername);
		admin.setLastip(ip);
		// 取时间
		LocalDateTime lasttime = LocalDateTime.now();
		admin.setLasttime(lasttime);
		int detail = adminMapper.insert(admin);
		// 更新角色
		List<Integer> roleids = admin.getRoleId();
		Integer id = admin.getId();
		List<RoleUsers> inserts = new ArrayList<>();
		roleids.forEach(item -> {
			RoleUsers tmp = new RoleUsers();
			tmp.setRoleId(item);
			tmp.setUserId(id);
			inserts.add(tmp);
		});
		roleuserService.saveBatch(inserts);
		return detail;
	}
	//	更新单条
	@Transactional
	public Integer updateadmin(AdminRequest admin) {
		int detail = adminMapper.updateById(admin);
		// 删除关联的信息
		QueryWrapper<RoleUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",admin.getId());
		roleUserMapper.delete(queryWrapper);
		// 更新角色
		List<Integer> roleids = admin.getRoleId();
		Integer id = admin.getId();
		List<RoleUsers> inserts = new ArrayList<>();
		roleids.forEach(item -> {
			RoleUsers tmp = new RoleUsers();
			tmp.setRoleId(item);
			tmp.setUserId(id);
			inserts.add(tmp);
		});
		roleuserService.saveBatch(inserts);
		return detail;
	}
	//	更新密码
	public Object updatepwd(AdminRequest admin) {
		// 密码是否相等
		String pwd = admin.getPassword();
		String pwd2 = admin.getRepassword();
		if (!pwd.equals(pwd2)) {
			return ResultUtil.error(400, "两次密码不同");
		}
		// 加密，秘钥
		String crypt = RandomUtil.randomString(8);
		admin.setCrypt(crypt);
		String pwd3 = SecureUtil.md5(SecureUtil.md5(admin.getPassword() + "." + crypt + "." + admin.getPassword()));
		admin.setPassword(pwd3);
		int detail = adminMapper.updateById(admin);
		return detail;
	}
	//	删除单条
	@Transactional
	public Object remove(AdminRequest admin) throws Exception {
		// 判断是不是创始人
		if(admin.detailId == 1) {
			return ResultUtil.error(403, "创始人不能删除");
		}
		// 删除关联的信息
		QueryWrapper<RoleUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",admin.detailId);
		roleUserMapper.delete(queryWrapper);
		int res = adminMapper.deleteById(admin.detailId);
		return res;
	}
}
