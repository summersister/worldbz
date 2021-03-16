package com.dear.domain.bbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostDto {

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("发帖人")
    private Integer userId;

    @ApiModelProperty("社区id")
    private Integer comId;

}