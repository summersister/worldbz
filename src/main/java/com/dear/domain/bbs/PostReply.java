package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class PostReply {

    private Integer id;

    private Integer postId;

    private Integer userId;

    private String content;

    private Integer number;

    private Date createTime;

    private Boolean del;

}