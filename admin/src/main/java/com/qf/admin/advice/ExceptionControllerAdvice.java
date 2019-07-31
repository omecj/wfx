package com.qf.admin.advice;

import com.qf.common.http.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author-izumi
 */
@ControllerAdvice   //Controller增强，提供统一的全局异常处理
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)  //标识当前方法是错误异常处理方法
    @ResponseBody                       //返回消息体（Ajax的返回值）
    public Result<?> handlerBusinessException(HttpServletRequest request, Exception e) {
        return Result.error(e.getMessage());
    }
}
