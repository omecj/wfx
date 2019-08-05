package com.qf.zmt.shiro;

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
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        String pass = new String(password);
        MerchantUser merchantUser = merchantUserService.getByUsername(username);
        LoginMerchant loginMerchant = new LoginMerchant();
        if (merchantUser == null){
            throw new RuntimeException("username is not exist");
        }
        if (!pass.equals(merchantUser.getPassword())) {
            throw new RuntimeException("password is wrong");
        }
        Field[] MerchantFields = merchantUser.getClass().getDeclaredFields();
        Field[] loginMerchantFields = loginMerchant.getClass().getDeclaredFields();
        //for (Field MerchantField : MerchantFields) {
        //    for (Field loginMerchantField : loginMerchantFields) {
        //        if (loginMerchantField==MerchantField){
        //
        //        }
        //    }
        //}
        //BeanUtils.copyProperties(loginMerchant, merchantUser);
        loginMerchant.setId(merchantUser.getId());
        loginMerchant.setUserName(merchantUser.getUserName());
        //return new SimpleAuthenticationInfo(loginMerchant, merchantUser.getPassword(), merchantUser.getUserName());
        return new SimpleAuthenticationInfo(loginMerchant,merchantUser.getPassword(),merchantUser.getName());
    }
}
