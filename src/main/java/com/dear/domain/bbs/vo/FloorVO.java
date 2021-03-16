package com.dear.domain.bbs.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FloorVO {

    private Integer id;

    private Integer postId;

    private Integer replyId;

    private Integer userId;

    private String content;

    private Date createTime;

    @ApiModelProperty("发帖人头像")
    private String headUrl;

    @ApiModelProperty("发帖人名")
    private String nickName;
}