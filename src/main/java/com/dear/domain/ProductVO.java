package com.dear.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ProductVO {

    private Integer id;

    private String title;

    private String content;

    private String imgUrl;

    private boolean del;

    private Date createTime;
}