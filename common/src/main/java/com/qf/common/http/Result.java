package com.qf.common.http;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * HTTP请求体
 *
 * @param <T>
 * @author Pegasus
 */
@JsonSerialize
@JsonInclude(value = Include.NON_NULL)      //允许字段为空
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;           //状态编码
    private String message;         //响应信息
    private Boolean result;         //是否成功. (true:成功; false:失败)
    private T data;                 //返回数据
    private Long count;


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }



    public Result() {
    }
    private Result(int code, String message, Boolean result, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.result = result;
    }

    public static <T> Result<T> create(ResultCode respondCode, T data) {
        return new Result<T>(respondCode.getCode(), respondCode.getDescription(), respondCode.getResult(), data);
    }
    public static <T> Result<T> create(ResultCode respondCode) {
        return new Result<T>(respondCode.getCode(), respondCode.getDescription(), respondCode.getResult(), null);
    }

    /**
     * 创建成功信息
     * @param data 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), null, ResultCode.SUCCESS.getResult(), data);
    }
    /**
     * 创建成功信息
     * @param page 返回给客户端的信息
     * @return
     */
    public static <T> Result<List<T>> success(IPage<T> page) {
        Result<List<T>> result = new Result<List<T>>(ResultCode.SUCCESS.getCode(), null,
                ResultCode.SUCCESS.getResult(), page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }
    /**
     * 创建成功信息
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>(ResultCode.SUCCESS.getCode(), null, ResultCode.SUCCESS.getResult(), null);
    }


    /**
     * 创建失败信息
     * @param data 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> error(T data) {
        return new Result<T>(ResultCode.ERROR.getCode(), null, ResultCode.ERROR.getResult(), data);
    }
    /**
     * 创建失败信息
     * @param message 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> error(String message) {
        return new Result<T>(ResultCode.ERROR.getCode(), message, ResultCode.ERROR.getResult(),null);
    }
    /**
     * 创建失败信息
     * @return
     */
    public static <T> Result<T> error() {
        return new Result<T>(ResultCode.ERROR.getCode(),null, ResultCode.ERROR.getResult(),null);
    }

}