package com.dear.domain.bbs.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostBasicDataVO {

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("发帖人")
    private Integer userId;

    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("是否置顶")
    private Boolean good;

    @ApiModelProperty("发帖人头像")
    private String headUrl;

    @ApiModelProperty("发帖人名")
    private String nickName;

    @ApiModelProperty("回复数")
    private Integer replyCount;

}