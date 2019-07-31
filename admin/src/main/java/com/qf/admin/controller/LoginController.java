package com.qf.admin.controller;

import com.qf.common.http.Result;
import com.qf.service.impl.AdminServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author-izumi
 */
@Controller
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping({"/", "login"})
    public String toLogin() {
        return "login";
    }

    @RequestMapping("login.do")
    public String doLogin(Model model,
                          @RequestParam(value = "username", required = false,defaultValue = "") String username,
                          @RequestParam(value = "password", required = false,defaultValue = "") String password) {
        //String类型默认值为"",Integer类型默认为null
        if (username.equals("")){
            return "forward:/";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()){
                return "forward:/toIndex";
            }
        }catch (AuthenticationException exception){
            exception.printStackTrace();
        }
        model.addAttribute("data", Result.error("账号或密码错误！"));
        model.addAttribute("username", username);
        model.addAttribute("error", "账号或密码错误");
        //return "redirect:/login";
        return "forward:/";
        //return "login";

        /**
         * redirect:重定向，只能传值给下一个环，不能跨环节，跨环节时，需要下一环去接，或者放在session里面
         * forward:请求分发，可以跨环节传值
         */

        //------------------------------------------------------------------------
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //Subject subject = SecurityUtils.getSubject();
        //try {
        //    subject.login(token);
        //    if (subject.isAuthenticated()){
        //        return Result.create(ResultCode.LOGIN_SUCCESS);
        //    }
        //}catch (AuthenticationException ae){
        //}
        //return Result.create(ResultCode.LOGIN_ERROR);

        //-------------------------------------------------------------------------
        //UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //Subject subject = SecurityUtils.getSubject();
        //try {
        //    subject.login(token);
        //    if (subject.isAuthenticated()){
        //        return Constants.LOGIN_SUCCESS;
        //    }
        //}catch (AuthenticationException exception){
        //}
        //return Constants.LOGIN_FAILED;

        //-------------------------------------------------------------------------
        //Admin admin = adminService.getByUsername(username);
        //if (admin == null || password.equals(admin.getPassword())){
        //    return "账号或密码错误";
        //}
        //return "欢迎！";
    }

}
