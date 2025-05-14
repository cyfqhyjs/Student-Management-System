package com.liujiajun.realm;

import com.liujiajun.po.Role;
import com.liujiajun.po.Userlogin;
import com.liujiajun.service.RoleService;
import com.liujiajun.service.UserloginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserloginService userloginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;



    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            Userlogin userlogin = userloginService.findByName(username);
            role = roleService.findByid(userlogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        if (role != null) {
            roles.add(role.getRolename());
            info.setRoles(roles);
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        Userlogin userlogin = null;
        try {
            userlogin = userloginService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownAccountException("查询用户信息时出错");
        }

        if (userlogin == null) {
            throw new UnknownAccountException("用户名不存在");
        }

        // 返回认证信息，包含用户名和加密后的密码
        return new SimpleAuthenticationInfo(
                userlogin.getUsername(), // 用户名
                userlogin.getPassword(), // 数据库中存储的加密密码
                getName() // 当前 Realm 的名称
        );
    }
}