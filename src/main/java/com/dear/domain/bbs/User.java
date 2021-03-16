package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    private String userName;

    private String passWord;

    private String nickName;

    private String headUrl;

    private Byte isFrozen;

    private Byte del;

    private Date createTime;

    private Date updateTime;
}