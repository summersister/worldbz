package com.richman.domain.c2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TradeCreditworthiness(c2c用户信息)")
public class TradeCreditworthiness {

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "成功单数")
    private Integer successCount;

    @ApiModelProperty(value = "失败单数")
    private Integer failedCount;

    @ApiModelProperty(value = "胜诉次数")
    private Integer winCount;

    @ApiModelProperty(value = "败诉次数")
    private Integer loseCount;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

    @ApiModelProperty(value = "总用时")
    private Long totalUsedTime;

    @ApiModelProperty(value = "总完成单数")
    private Integer totalFinishCount;

    @ApiModelProperty(value = "修改昵称标识-> 0不可修改，1可修改")
    private Boolean modify;   //redgi 2018-08-30 (用户修改昵称策略更改新增字段)

}