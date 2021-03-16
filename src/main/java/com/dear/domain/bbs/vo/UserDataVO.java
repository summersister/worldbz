package com.dear.domain.bbs.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserDataVO {

    private Integer userId;

    private String userName;

    private String nickName;

    private String headUrl;

    private byte sex;

    private String sign;

    private Date birthday;
}