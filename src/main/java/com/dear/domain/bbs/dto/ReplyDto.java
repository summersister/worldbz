package com.dear.domain.bbs.dto;

import lombok.Data;

@Data
public class ReplyDto {

    private String content;

    private Integer postId;

    private Integer userId;

}