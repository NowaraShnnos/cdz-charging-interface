package com.pengniao.cdzcharginginterface.response;

/** 功能描述: 通用响应状态
 * @param null
* @return:
* @Author: lj
* @Date: 2019/11/22 15:59
*/
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(0,"操作成功！"),

    /* 错误状态码 */
    FAIL(-1,"操作失败！"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数格式错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),
    USER_LOGIN_ACCOUNTERROR(20002, "账号不存在"),
    USER_LOGIN_PASSWORDERROR(20003, "密码错误"),
    USER_ACCOUNT_FORBIDDEN(20004, "账号已被禁用"),
    USER_NOT_EXIST(20005 ,"用户不存在"),
    USER_HAS_EXISTED(20006, "用户已存在，请重新填写"),
    USER_LOGIN_CAPTCHAERROR(20007, "验证码错误"),
    USER_DELETESYS_ACCOUNTERROR(20008, "系统管理员不允许删除"),
    USER_DELETECUR_ACCOUNTERROR(20009, "当前用户不允许删除"),
    USER__OLDPASSWORDERROR(20010, "原密码错误"),
    USER_DELETEENTERPRISE_ERROR(20011, "当前企业不允许删除"),
    USER_TENANTADMIN_EXISTS(20012, "当前租户管理员已存在"),

    /* 业务错误：30001-39999 */


    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),


    /* 数据错误：50001-599999 */
    SYSTEM_SQL_FOREIGNKEY(50001, "数据正在使用，无法删除"),

    /* 接口错误：60001-69999 */



    /* 权限错误：70001-79999 */
    PERMISSION_UNAUTHENTICATED(70001,"此操作需要登陆系统！"),
    PERMISSION_UNAUTHORISE(70002,"权限不足，无权操作！"),
    PERMISSION_EXPIRE(70003,"登录状态过期！"),
    PERMISSION_TOKEN_EXPIRED(70004, "token已过期"),
    PERMISSION_LIMIT(70005, "访问次数受限制"),
    PERMISSION_TOKEN_INVALID(70006, "无效token"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败"),
    PERMISSION_MENU_PROHIBITDELETE(70008, "系统菜单不允许删除"),
    PERMISSION_MENU_CHILDERDELETE(70009, "请先删除子菜单或者按钮"),

     /*定时任务*/
     SCHEDULEJOB_CRONTRIGGER(80001, "获取定时任务CronTrigger出现异常"),
    SCHEDULEJOB_CREATE_ERROR(80002, "创建定时任务失败"),
    SCHEDULEJOB_UPDATE_ERROR(80003, "更新定时任务失败"),
    SCHEDULEJOB_EXECUTE_ERROR(80004, "立即执行定时任务失败"),
    SCHEDULEJOB_PAUSE_ERROR(80005, "暂停定时任务失败"),
    SCHEDULEJOB_RENEW_ERROR(80006, "恢复定时任务失败"),
    SCHEDULEJOB_DELETE_ERROR(80007, "删除定时任务失败");

    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
