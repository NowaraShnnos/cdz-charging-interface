package com.pengniao.cdzcharginginterface.exception;


import com.pengniao.cdzcharginginterface.response.ResultCode;

import java.text.MessageFormat;

/** 功能描述: 自定义异常类型
 * @param null
* @return:
* @Author: lj
* @Date: 2019/11/23 10:56
*/
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }

}
