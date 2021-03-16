package com.dear.domain.bbs.dto;

import lombok.Data;

@Data
public class PostReplyFloorDto {

    private Integer replyId;

    private Integer userId;

    private String content;

}