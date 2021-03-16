package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetails {

    private Integer userId;

    private byte sex;

    private Date birthday;

    private String sign;

    private Date updateTime;
}