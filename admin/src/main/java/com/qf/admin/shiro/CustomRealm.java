package com.qf.admin.shiro;

import com.qf.entity.dto.Admin;
import com.qf.entity.po.LoginAdmin;
import com.qf.service.impl.AdminServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author-izumi
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        Set<String> permissionSet = new HashSet<>();
//        permissionSet.add("ADMINISTRATOR");
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
//        return simpleAuthorizationInfo;

        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        String pass = new String(password);
        Admin admin = adminService.getByUsername(username);
        if (admin == null){
            throw new RuntimeException("username is not exists");
        }
        if (!pass.equals(admin.getPassword())){
            throw new RuntimeException("password is wrong!");
        }
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.setId(admin.getId());
        loginAdmin.setRoleId(admin.getRoleId());
        loginAdmin.setAccount(admin.getAccount());
        loginAdmin.setName(admin.getName());
        loginAdmin.setDescription(admin.getDescription());
        //Object principal, Object credentials, String realName
        return new SimpleAuthenticationInfo(loginAdmin,admin.getPassword(),admin.getName());
    }
}
