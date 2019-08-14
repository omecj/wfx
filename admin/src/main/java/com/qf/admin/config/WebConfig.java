package com.qf.admin.config;

import com.qf.admin.interceptor.LoginInterceptor;
import com.qf.admin.resolver.ArgumentResolver;
import com.qf.common.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

/**
 * @Author-izumi
 */
//Spring-mvc的配置类
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    //添加参数解析器
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ServletWebArgumentResolverAdapter(new ArgumentResolver()));
    }
    //配置SpringBoot的静态资源
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:static/fonts/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:static/favicon.ico");
        super.addResourceHandlers(registry);
    }
    //配置Spring-mvc拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())     //权限拦截器
                .addPathPatterns("/**")                 //拦截所有
                .excludePathPatterns(Constants.LIST);   //排除拦截项
        super.addInterceptors(registry);
    }
}
