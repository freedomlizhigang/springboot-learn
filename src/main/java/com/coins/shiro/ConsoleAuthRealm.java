package com.coins.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.coins.rbac.entity.Admins;
import com.coins.rbac.entity.Roles;
import com.coins.rbac.mapper.RolePrivsMapper;
import com.coins.utils.JWTUtil;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

// 管理后台权限验证
@Component
public class ConsoleAuthRealm extends AuthorizingRealm {

	@Autowired
	private RolePrivsMapper rolePrivMapper;
	/**
     * 必须重写此方法，不然Shiro会报错
     */
    
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    
    /**
     * 认证信息(身份验证)
     * Authentication 是用来验证用户身份，判断用户是不是存在等状态
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
       
        System.out.println("认证方式 -- Token############" + token);
        
        return new SimpleAuthenticationInfo(token, token, "console_realm");
    }
    
    /**
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principals
     * @return
     */
    
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
    
        String token = principals.toString();
        System.out.println("授权书 --  Token############" + token);

//        String username = JWTUtil.getUsername(principals.toString());
//        // 下面的可以使用缓存提升速度
//        ManagerInfo managerInfo = managerInfoService.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //设置相应角色的权限信息
//        for (SysRole role : managerInfo.getRoles()) {
//            //设置角色
//            authorizationInfo.addRole(role.getRole());
//            for (Permission p : role.getPermissions()) {
//                //设置权限
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        authorizationInfo.addStringPermission("add");
        
        return authorizationInfo;
    }
}
