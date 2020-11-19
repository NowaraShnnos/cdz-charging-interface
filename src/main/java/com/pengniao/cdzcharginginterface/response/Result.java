package com.pengniao.cdzcharginginterface.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;


/** 功能描述: 统一响应结果集
 * @param null
* @return:
* @Author: lj
* @Date: 2019/11/22 15:55
*/

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Result<T> extends HashMap<String, Object> {

   private int code;  //操作代码
   private String message;  //提示信息
   private T data;   //结果数据

    public Result(){}

    public Result(ResultCode resultCode){
        super.put("code", resultCode.code());
        super.put("message", resultCode.message());
    }

    public Result(ResultCode resultCode, String message){
        super.put("code", resultCode.code());
        super.put("message", message);
    }



    public Result(ResultCode resultCode, T data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public Result(String message){
        this.message = message;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result SUCCESS(T data){
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String message){
        return new Result(ResultCode.FAIL, message);
    }

    public static Result FAIL(int code, String message){

        Result r = new Result();
        r.put("code",code);
        r.put("msg",message);
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
