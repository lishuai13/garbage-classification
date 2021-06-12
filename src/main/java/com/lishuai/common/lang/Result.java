package com.lishuai.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  统一结果封装
 * </p>
 *
 * @author lishuai
 * @since 2021-04-10
 */
@Data
public class Result implements Serializable {
    /**
     * 状态码、信息、数据
     */
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功,有返回值
     * @param  data
     * @return Result
     */
    public static Result success(Object data){
        if (data == null){
            return noContent();
        }
        return new Result(200,"请求成功,有返回值！",data);
    }

    /**
     * 请求成功,有返回值
     * @return Result
     */
    public static Result success(){
        return new Result(200,"请求成功,无返回值！",null);
    }

    /**
     * 请求成功,无返回值
     * @return Result
     */
    public static Result noContent(){
        return new Result(-1,"查询结果为空！",null);
    }

    /**
     * 请求成功,有返回值
     * @return Result
     */
    public static Result invalid(){
        return new Result(-1,"请求参数错误！",null);
    }

    /**
     * 请求成功,有返回值
     * @return Result
     */
    public static Result error(){
        return new Result(-1,"服务端错误！",null);
    }

    /**
     * 请求失败
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Result fail(int code, String message, Object data){
        return new Result(code,message,data);
    }
}
