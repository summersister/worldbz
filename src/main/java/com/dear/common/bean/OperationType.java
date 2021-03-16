package com.dear.common.bean;

public enum OperationType {

    REGISTER(1004, "注册"),
    LOGIN(1005, "登录"),

    TEST(99999, "TEST");


    OperationType(Integer code, String message) {
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
