package com.richman.domain.c2c.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ChatContentDTO(聊天内容参数)")
public class ChatContentDTO implements Serializable {

    @ApiModelProperty(value = "订单ID", hidden = true)
    private String orderId;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "发送方", required = true)
    private String from;

    @ApiModelProperty(value = "接受方", required = true)
    private String to;

    @ApiModelProperty(value = "内容", required = true)
    private String content;

    @ApiModelProperty(value = "时间", hidden = true)
    private Long time;

    @ApiModelProperty(value = "来源", required = true)
    private String source;

}
