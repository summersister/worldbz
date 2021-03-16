package com.dear.domain.bbs;

import lombok.Data;

import java.util.Date;

@Data
public class PostReplyFloor {

    private Integer id;

    private Integer postId;

    private Integer replyId;

    private Integer userId;

    private String content;

    private Integer number;

    private Boolean del;

    private Date createTime;
}