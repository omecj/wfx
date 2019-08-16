package com.qf.admin.interceptor;


import com.qf.common.constant.Constants;
import com.qf.entity.po.LoginAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author-izumi
 */
//SpringBoot的拦截器，需要继承自HandlerInterceptorAdapter
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //当前处理器是Controller类中的方法时，才做相应的业务逻辑
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            Subject subject = SecurityUtils.getSubject();
            LoginAdmin loginadmin = (LoginAdmin) subject.getPrincipal();
            if (loginadmin == null) {
                response.sendRedirect("/");
                //throw new RuntimeException(ResultCode.LOGIN_ERROR.getDescription());
            }
            request.setAttribute(Constants.CURRENT_ADMIN, loginadmin);
        }
        return super.preHandle(request, response, handler);
    }

}
