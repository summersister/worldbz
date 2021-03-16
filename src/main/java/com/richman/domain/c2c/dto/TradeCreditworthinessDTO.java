package com.richman.domain.c2c.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "信誉")
public class TradeCreditworthinessDTO {


    @ApiModelProperty(value = "用户ID",required = true)
    private Integer id;
    @ApiModelProperty(value = "成功单数",required = true)
    private Integer successCount;
    @ApiModelProperty(value = "失败单数",required = true)
    private Integer failedCount;
    @ApiModelProperty(value = "胜诉次数",required = true)
    private Integer winCount;
    @ApiModelProperty(value = "败诉次数",required = true)
    private Integer loseCount;
    @ApiModelProperty(value = "总用时",required = true)
    private Long totalUsedTime;
    @ApiModelProperty(value = "正常放币完成单数")
    private Integer totalFinishCount;
}