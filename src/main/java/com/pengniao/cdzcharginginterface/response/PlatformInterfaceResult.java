package com.pengniao.cdzcharginginterface.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;

/**
 * @program: cdz-charging-interface
 * @description: 平台接口的返回值
 * @author: lj
 * @create: 2020-11-20 11:31
 **/
public class PlatformInterfaceResult {

    private Integer code; //  返回结果代码

    private String message; //  返回结果

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
}
