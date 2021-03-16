package com.dear.domain.bbs.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostReplyDataVO {

    private Integer id;

    private Integer postId;

    private Integer userId;

    @ApiModelProperty("发帖人头像")
    private String headUrl;

    @ApiModelProperty("发帖人名")
    private String nickName;

    private String content;

    @ApiModelProperty("楼层")
    private Integer number;

    private Date createTime;

    private List<FloorVO> floorList;

}