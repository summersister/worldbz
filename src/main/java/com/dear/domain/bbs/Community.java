package com.dear.domain.bbs;

import lombok.Data;

@Data
public class Community {

    private Integer id;

    private String title;

    private String content;

    private String headUrl;

    private Long createTime;

    private Long updateTime;
}