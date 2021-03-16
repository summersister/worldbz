package com.dear.domain.bbs;

import lombok.Data;

@Data
public class FileUrl {

    private Integer id;

    private Integer userId;

    private String url;

    private Long createTime;
}