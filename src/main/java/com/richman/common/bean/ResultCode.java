package com.richman.common.bean;

public enum ResultCode {

    //通用部分
    OK(0, "成功"),
    ERROR(1, "失败"),
    NOPERM(2, "未登录"),
    USER_NAME_EXISTS(4001, "用户名已存在"),
    PASSWORD_ERROR(4002, "用户名或密码错误"),
    POST_REPLY_NOT_FOUND(4003, "找不到相关帖子"),
    USER_IS_FROZEN(4004, "账户被冻结"),
    USER_NICK_EXISTS(4005, "昵称已存在"),

    TEST(99999, "TEST");


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

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
