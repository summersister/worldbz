package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class Post {

    private Integer id;

    private Integer comId;

    private Integer userId;

    private String title;

    private String content;

    private Boolean good;

    private Boolean hide;

    private Boolean del;

    private Boolean top;

    private Date createTime;

    private Integer delUser;

    private Integer replyCount;

}