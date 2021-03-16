package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class UserIp {

    private Integer id;

    private Integer userId;

    private String ip;

    private Integer type;

    private Date createTime;

}