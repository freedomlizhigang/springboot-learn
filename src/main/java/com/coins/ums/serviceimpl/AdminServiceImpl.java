package com.coins.ums.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.ums.entity.*;
import com.coins.ums.mapper.AdminMapper;
import com.coins.ums.request.AdminRequest;
import com.coins.ums.service.*;
import com.coins.utils.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IRoleAdminService roleAdminService;
    @Autowired
    @Lazy
    private IRoleService roleService;
    @Autowired
    private IRolePrivService rolePrivService;
    @Autowired
    private IDepartmentAdminService departmentAdminService;
    @Autowired
    @Lazy
    private IDepartmentService departmentService;
    @Autowired
    private AdminMapper adminMapper;

    //	登录
    public Object postLogin(Admin admin) throws Exception {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", admin.getName()).eq("del_flag",0);
        Admin detail = this.getOne(queryWrapper);
        if (detail == null){
            throw new CoinException(403,"没有找到此用户");
        }
        // 密码判断
        String passwd = CryptUtils.encryptToMD5(CryptUtils.encryptToMD5(admin.getPassword() + "." + detail.getCrypt() + "." + admin.getPassword()));
        if (!passwd.equals(detail.getPassword())){
            throw new CoinException(403,"密码不正确");
        }
        if (detail.getStatus() != 1){
            throw new CoinException(403,"用户被禁用");
        }
        // 找所有角色，菜单权限，所属部门
        QueryWrapper<RoleAdmin> queryRoleAdmin = new QueryWrapper<>();
        queryRoleAdmin.eq("admin_id",detail.getId()).select("role_id");
        List<Object> roleIds = roleAdminService.listObjs(queryRoleAdmin);
        QueryWrapper<RolePriv> queryRolePriv = new QueryWrapper<>();
        queryRolePriv.in("role_id",roleIds).select("url");
        List<Object> urls = rolePrivService.listObjs(queryRolePriv);
        QueryWrapper<DepartmentAdmin> queryDepartmentAdmin = new QueryWrapper<>();
        queryDepartmentAdmin.eq("admin_id",detail.getId());
        List<Object> departmentIds = departmentAdminService.listObjs(queryDepartmentAdmin);
        // 更新ip，登录时间
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", detail.getId());
        updateWrapper.set("lastip", request.getRemoteAddr());
        updateWrapper.set("lasttime", LocalDateTime.now());
        this.update(new Admin(), updateWrapper);
        // 生成token
        String username = (detail.getId()).toString();
        String token = JWTUtil.sign(username,7 * 24,"c-token");
        Map<String,Object> result = new HashMap<>();
        result.put("id",detail.getId());
        result.put("name",detail.getName());
        result.put("phone",detail.getPhone());
        result.put("token",token);
        result.put("roleIds",roleIds);
        result.put("departmentIds",departmentIds);
        result.put("urls",urls);
        // 开始存redis
        redisUtils.del("c-token:" + username);
        redisUtils.set("c-token:" + username,result,24*3600);
        // 不返回权限url
        result.remove("urls");
        return result;
    }

    // 获取列表，mapper自定义查询关联
    public Map<String, Object> getListAndRoleDepartment(AdminRequest adminRequest) throws Exception {
        List<Admin> all = adminMapper.getListAndRoleDepartment(adminRequest);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(adminRequest.key != null, "name", adminRequest.key).select("id");
        Long count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
    // 获取列表，mapper自定义查询关联
    public Map<String, Object> getList(AdminRequest adminRequest) throws Exception {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(adminRequest.key != null, "name", adminRequest.key);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + adminRequest.offset + "," + adminRequest.pageSize).select("id","name","phone","realname");
        List<Admin> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
    // 获取到user以后怎么处理
    /*System.out.println("-------------------list---------------------------");
    System.out.println(request.getParameter("coinUserMap"));
    String coinUser = request.getParameter("coinUserMap");
    Map<String,Object> jsonobject = JSONObject.parseObject(coinUser);
    System.out.println(jsonobject.get("priv"));
    Map<String,Object> priv = (Map<String, Object>) jsonobject.get("priv");
    System.out.println(priv.get("token1"));
    List label = (List) jsonobject.get("label");
    System.out.println(label.get(0));
    System.out.println("-------------------list---------------------------");*/

    //	获取单条，返回字段控制
    public Object getDetail(AdminRequest admin) {
        Object detail = adminMapper.getDetailAndRoleDepartmentIds(admin.detailId);
        return detail;
    }

    //	插入单条
    @Transactional
    public Object create(Admin admin) throws Exception {
        // 密码是否相等
        String pwd = admin.getPassword();
        String pwd2 = admin.getRepassword();
        if (!pwd.equals(pwd2)) {
            throw new CoinException(400, "两次密码不同");
        }
        // 加密，秘钥
        String crypt = FuncUtils.getRandomString(10);
        admin.setCrypt(crypt);
        String pwd3 = CryptUtils.encryptToMD5(CryptUtils.encryptToMD5(admin.getPassword() + "." + crypt + "." + admin.getPassword()));
        admin.setPassword(pwd3);
        // 取ip
        String[] headername = {};
        String ip = request.getRemoteAddr();
        admin.setLastip(ip);
        // 取时间
        LocalDateTime lasttime = LocalDateTime.now();
        admin.setLasttime(lasttime);
        Boolean result = this.save(admin);
        // 更新角色
        List<Integer> roleids = admin.getRoleIds();
        Integer id = admin.getId();
        List<RoleAdmin> inserts = new ArrayList<>();
        roleids.forEach(item -> {
            RoleAdmin tmp = new RoleAdmin();
            tmp.setRoleId(item);
            tmp.setAdminId(id);
            inserts.add(tmp);
        });
        roleAdminService.saveBatch(inserts);
        // 更新部门
        List<Integer> departmentIds = admin.getDepartmentIds();
        List<DepartmentAdmin> insertDepartment = new ArrayList<>();
        departmentIds.forEach(item -> {
            DepartmentAdmin tmp = new DepartmentAdmin();
            tmp.setDepartmentId(item);
            tmp.setAdminId(id);
            insertDepartment.add(tmp);
        });
        departmentAdminService.saveBatch(insertDepartment);
        return result;
    }

    //	更新单条
    @Transactional
    public Boolean updateadmin(Admin admin) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", admin.getId());
        updateWrapper.set("realname", admin.getRealname());
        updateWrapper.set("phone", admin.getPhone());
        updateWrapper.set("email", admin.getEmail());
        Boolean result = this.update(new Admin(), updateWrapper);
        // 删除关联的信息
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id", admin.getId());
        roleAdminService.remove(queryWrapper);
        // 更新角色
        List<Integer> roleids = admin.getRoleIds();
        Integer id = admin.getId();
        List<RoleAdmin> inserts = new ArrayList<>();
        roleids.forEach(item -> {
            RoleAdmin tmp = new RoleAdmin();
            tmp.setRoleId(item);
            tmp.setAdminId(id);
            inserts.add(tmp);
        });
        roleAdminService.saveBatch(inserts);
        // 删除关联的信息
        QueryWrapper<DepartmentAdmin> queryDepartmentWrapper = new QueryWrapper<>();
        queryDepartmentWrapper.eq("admin_id", admin.getId());
        departmentAdminService.remove(queryDepartmentWrapper);
        // 更新部门
        List<Integer> departmentIds = admin.getDepartmentIds();
        List<DepartmentAdmin> insertDepartment = new ArrayList<>();
        departmentIds.forEach(item -> {
            DepartmentAdmin tmp = new DepartmentAdmin();
            tmp.setDepartmentId(item);
            tmp.setAdminId(id);
            insertDepartment.add(tmp);
        });
        departmentAdminService.saveBatch(insertDepartment);
        return result;
    }

    //	更新密码
    public Object updatepwd(Admin admin) throws Exception {
        // 密码是否相等
        String pwd = admin.getPassword();
        String pwd2 = admin.getRepassword();
        if (!pwd.equals(pwd2)) {
            throw new CoinException(400, "两次密码不同");
        }
        // 加密，秘钥
        String crypt = FuncUtils.getRandomString(10);
        String pwd3 = CryptUtils.encryptToMD5(CryptUtils.encryptToMD5(admin.getPassword() + "." + crypt + "." + admin.getPassword()));
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", admin.getId());
        updateWrapper.set("crypt", crypt);
        updateWrapper.set("password", pwd3);
        Boolean result = this.update(new Admin(), updateWrapper);
        // 清除登录状态
        redisUtils.del("c-token:" + admin.getId());
        return result;
    }

    //	更新单条状态
    public Boolean updatestatus(Admin admin) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", admin.getId());
        updateWrapper.set("status", admin.getStatus());
        Boolean detail = this.update(new Admin(), updateWrapper);
        // 清除登录状态
        redisUtils.del("c-token:" + admin.getId());
        return detail;
    }


    // 修改个人资料
    public Boolean updateSelf(Admin admin) throws JsonProcessingException {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        String coinUser = request.getParameter("coinUserMap");
        Map<String,Object> userInfo = JsonUtil.str2Obj(coinUser,Map.class);
        updateWrapper.eq("id", userInfo.get("id"));
        updateWrapper.set("realname", admin.getRealname());
        updateWrapper.set("phone", admin.getPhone());
        updateWrapper.set("email", admin.getEmail());
        Boolean result = this.update(new Admin(), updateWrapper);
        return result;
    }

    //	修改密码密码
    public Object updateSelfPwd(Admin admin) throws Exception {
        // 密码是否相等
        String pwd = admin.getPassword();
        String pwd2 = admin.getRepassword();
        if (!pwd.equals(pwd2)) {
            throw new CoinException(400, "两次密码不同");
        }
        // 加密，秘钥
        String crypt = FuncUtils.getRandomString(10);
        String pwd3 = CryptUtils.encryptToMD5(CryptUtils.encryptToMD5(admin.getPassword() + "." + crypt + "." + admin.getPassword()));
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        String coinUser = request.getParameter("coinUserMap");
        Map<String,Object> userInfo = JsonUtil.str2Obj(coinUser,Map.class);
        updateWrapper.eq("id", userInfo.get("id"));
        updateWrapper.set("crypt", crypt);
        updateWrapper.set("password", pwd3);
        Boolean result = this.update(new Admin(), updateWrapper);
        // 清除登录状态
        redisUtils.del("c-token:" + admin.getId());
        return result;
    }
    //	删除单条
    @Transactional
    public Object remove(AdminRequest admin) {
        // 判断是不是创始人
        if (admin.detailId == 1) {
            throw new CoinException(403, "创始人不能删除");
        }
        // 删除关联的信息
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id", admin.detailId);
        roleAdminService.remove(queryWrapper);
        QueryWrapper<DepartmentAdmin> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("admin_id", admin.detailId);
        departmentAdminService.remove(queryWrapper2);
        Boolean res = this.removeById(admin.detailId);
        // 清除登录状态
        redisUtils.del("c-token:" + admin.detailId);
        return res;
    }

    @Transactional
    public Object removeMultiple(AdminRequest admin) throws Exception {
        // 判断是不是创始人
        if (admin.detailIds.contains(1)) {
            throw new CoinException(403, "创始人不能删除");
        }
        // 同时删除关联的权限信息
        QueryWrapper<RoleAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("admin_id", admin.detailIds);
        roleAdminService.remove(queryWrapper);
        QueryWrapper<DepartmentAdmin> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.in("admin_id", admin.detailId);
        departmentAdminService.remove(queryWrapper2);
        Boolean res = this.removeByIds(admin.detailIds);
        // 清除登录状态
        admin.detailIds.forEach(item -> redisUtils.del("c-token:" + item));
        return res;
    }


    // 通过querywrapper获取列表及对应关联
    public Map<String, Object> getListQuery(AdminRequest adminList) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(adminList.key != null, Admin::getName, adminList.key);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + adminList.offset + "," + adminList.pageSize);
        List<Admin> all = this.list(queryWrapper);
        List<Integer> adminIds = all.stream().map((Admin admin) -> admin.getId()).collect(Collectors.toList());
        // 开始查部门与角色
        QueryWrapper<DepartmentAdmin> departmentAdminQueryWrapper = new QueryWrapper<>();
        departmentAdminQueryWrapper.in("admin_id", adminIds);
        List<DepartmentAdmin> departmentAdmins = departmentAdminService.list(departmentAdminQueryWrapper);

        QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
        departmentQueryWrapper.in("id", departmentAdmins.stream().map(DepartmentAdmin::getDepartmentId).collect(Collectors.toList()));
        List<Department> departments = departmentService.list(departmentQueryWrapper);


        QueryWrapper<RoleAdmin> roleAdminQueryWrapper = new QueryWrapper<>();
        roleAdminQueryWrapper.in("admin_id", adminIds);
        List<RoleAdmin> roleAdmins = roleAdminService.list(roleAdminQueryWrapper);


        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.in("id", roleAdmins.stream().map(RoleAdmin::getRoleId).collect(Collectors.toList()));
        List<Role> roles = roleService.list(roleQueryWrapper);

        // 组合最终的返回数据，这里有点麻烦
        all.forEach(item -> {
            List<Integer> roleIds = roleAdmins.stream().filter((RoleAdmin roleAdmin) -> roleAdmin.getAdminId() == item.getId()).map(RoleAdmin::getRoleId).collect(Collectors.toList());
            List<Role> itemRoles = roles.stream().filter((Role role) -> roleIds.contains(role.getId())).collect(Collectors.toList());
            item.setRoles(itemRoles);
            List<Integer> departmentIds = departmentAdmins.stream().filter((DepartmentAdmin departmentAdmin) -> departmentAdmin.getAdminId() == item.getId()).map(DepartmentAdmin::getDepartmentId).collect(Collectors.toList());
            List<Department> itemDepartments = departments.stream().filter((Department department) -> departmentIds.contains(department.getId())).collect(Collectors.toList());
            item.setDepartments(itemDepartments);
        });
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }

    public Object postDetailBack(AdminRequest admin) {
//		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("id",admin.detailId);
//		Admin detail = this.getOne(queryWrapper);
//		QueryWrapper<RoleAdmin> roleAdminQueryWrapper = new QueryWrapper<>();
//		roleAdminQueryWrapper.eq("admin_id",admin.detailId).select("role_id");
//		List<RoleAdmin> roleAdmins = roleAdminService.list(roleAdminQueryWrapper);
//		QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
//		roleQueryWrapper.in("id",roleAdmins);
//		List<Role> roles = roleService.list(roleQueryWrapper);
//		detail.setRoles(roles);
//		这样处理需要返回的是对应的Model，不能是hashmap
//		List<Integer> roleIds = detail.getRoles().stream().map(Role -> Role.getId()).distinct().collect(Collectors.toList());
//		detail.setRoleIds(roleIds);
//		List<Integer> departmentIds = detail.getDepartments().stream().map(item -> item.getId()).collect(Collectors.toList());
//		detail.setDepartmentIds(departmentIds);
//		JsonSerializer cjs = new JsonSerializer();
//		cjs.filter(Admin.class,null,"crypt,password,repassword");
//		System.out.println(cjs.toJson(detail));
//		JSONObject jsonObj = JSONUtil.parseObj(cjs.toJson(detail));
//		System.out.println(jsonObj);
//		最原始的返回方式
//		Map<String,Object> result = new HashMap<>();
//		result.put("id",detail.getId());
//		result.put("name",detail.getName());
//		result.put("realname",detail.getRealname());
        Object detail = adminMapper.getDetailAndRoleDepartmentIds(admin.detailId);
        return detail;
    }
}
