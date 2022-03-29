package com.pearadmin.pro.common.web.domain.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Ajax 响 应 实 体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Accessors(chain = true)
public class Result<T> {

    /**
     * 状态码
     * */
    private Integer code;

    /**
     * 提示消息
     * */
    private String msg;

    /**
     * 返回结果
     * */
    private boolean success;

    /**
     * 携带数据
     * */
    private Object data;

    /**
     * 时间戳
     * */
    private long timeStamp = System.currentTimeMillis();

    /**
     * 权鉴
     * */
    private String tokenKey;

    private String token;

    private String loginTo;

    /**
     * 成 功 操 作
     * */
    public static<T> Result<T> success(){
        return success("");
    }

    /**
     * 成 功 操 作 , 携 带 数 据
     * */
    public static<T> Result<T> success(T data){
        return success(ResultCode.SUCCESS.message(),data);
    }
    public static<T> Result<T> lay_success(T data){
        return lay_success(ResultCode.LAY_SUCCESS.message(),data);
    }
    public static<T> Result<T> lay_success(String message,T data){
        return success(ResultCode.LAY_SUCCESS.code(),message,data);
    }

    /**
     * 成 功 操 作, 携 带 消 息
     * */
    public static<T> Result<T> success(String message){
        return success(message,null);
    }

    /**
     * 成 功 操 作, 携 带 消 息 和 携 带 数 据
     * */
    public static<T> Result<T> success(String message,T data){
        return success(ResultCode.SUCCESS.code(),message,data);
    }
    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * */
    public static<T> Result<T> success(ResultCode resultCode){
        return success(resultCode.code(),resultCode.message());
    }

    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * */
    public static<T> Result<T> success(ResultCode resultCode, String tokenKey, String token){
        Result result = success(resultCode.code(),resultCode.message());
        result.setTokenKey(tokenKey);
        result.setToken(token);
        return result;
    }
    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * */
    public static<T> Result<T> success(ResultCode resultCode, String tokenKey, String token,String loginTo){
        Result result = success(resultCode.code(),resultCode.message());
        result.setTokenKey(tokenKey);
        result.setToken(token);
        result.setLoginTo(loginTo);
        return result;
    }
    public static<T> Result<T> success(int code,String message){
        return success(code,message,null);
    }

    /**
     * 成 功 操 作, 携 带 自 定义 状 态 码, 消 息 和 数 据
     * */
    public static<T> Result<T> success(int code,String message,T data){
        return result(code,message,data,true);
    }

    /**
     * 失 败 操 作, 默 认 数 据
     * */
    public static<T> Result<T> failure(){
        return failure(ResultCode.FAILURE.message());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息
     */
    public static<T> Result<T> failure(String message){
        return failure(message,null);
    }

    /**
     * 失 败 操 作 , 携 带 自 定 义 消 息 , 状 态 码
     * */
    public static<T> Result<T> failure(ResultCode resultCode){
        return failure(resultCode.code(),resultCode.message());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息 和 数 据
     * */
    public static<T> Result<T> failure(String message,T data){
        return failure(ResultCode.FAILURE.code(),message,data);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 和 自 定 义 消 息
     * */
    public static<T> Result<T> failure(int code ,String message){
        return failure(code,message,null);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 , 消 息 和 数 据
     * */
    public static<T> Result<T> failure(int code,String message,T data){
        return result(code,message,data,false);
    }

    /**
     * Boolean 返 回 操 作, 携 带 默 认 返 回 值
     * */
    public static<T> Result<T> auto(boolean b){
        return auto(b, ResultCode.OPERATE_SUCCESS.message(), ResultCode.OPERATE_FAILURE.message());
    }

    /**
     * Boolean 返 回 操 作, 携 带 自 定 义 消 息
     * */
    public static<T> Result<T> auto(boolean b,String success,String failure){
        if(b){
            return success(success);
        }else{
            return failure(failure);
        }
    }

    /**
     * Result 构 建 方 法
     * */
    public static<T> Result<T> result(int code,String message,T data,boolean success){
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setSuccess(success);
        result.setTimeStamp(System.currentTimeMillis());
        result.setData(data);
        return result;
    }
    /**
     * Boolean 返 回 操 作, 携 带 默 认 返 回 值
     * */
    public static<T> Result<T> decide(boolean b){
        return decide(b, ResultCode.OPERATE_SUCCESS.message(), ResultCode.OPERATE_FAILURE.message());
    }

    /**
     * Boolean 返 回 操 作, 携 带 自 定 义 消 息
     * */
    public static<T> Result<T> decide(boolean b, String success, String failure){
        if(b){
            return success(success);
        }else{
            return failure(failure);
        }
    }

}
