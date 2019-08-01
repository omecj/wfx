package com.qf.merchant.shiro;

import com.qf.entity.dto.MerchantUser;
import com.qf.entity.po.LoginMerchant;
import com.qf.service.impl.MerchantUserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

/**
 * @Author-izumi
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private MerchantUserServiceImpl merchantUserService;

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
        MerchantUser merchantUser = merchantUserService.getByUsername(username);
        if (merchantUser == null){
            throw new RuntimeException("username is not exists");
        }
        if (!pass.equals(merchantUser.getPassword())){
            throw new RuntimeException("password is wrong!");
        }
        //字段名相同可以使用反射！！！
        Field[] fields = merchantUser.getClass().getDeclaredFields();
        
        LoginMerchant loginMerchant = new LoginMerchant();
        loginMerchant.setId(merchantUser.getId());
        loginMerchant.setUserName(merchantUser.getUserName());
        //Object principal, Object credentials, String realName
        return new SimpleAuthenticationInfo(loginMerchant,merchantUser.getPassword(),merchantUser.getName());
    }
}
