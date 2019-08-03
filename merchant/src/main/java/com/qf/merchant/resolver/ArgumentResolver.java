package com.qf.merchant.resolver;

import com.qf.common.constant.Constants;
import com.qf.entity.po.LoginMerchant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author-izumi
 */
//Spring-mvc的参数解析器，需要实现WebArgumentResolver接口
public class ArgumentResolver implements WebArgumentResolver {
    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        Class<?> parameterType = methodParameter.getParameterType();
        if (parameterType != null) {
            if (parameterType.equals(LoginMerchant.class)) {
                HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                return request.getAttribute(Constants.CURRENT_ADMIN);
            }
        }
        return UNRESOLVED;
    }
}
