package com.richman.domain.c2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "账单信息")
public class OtcTradeBill {
    @ApiModelProperty(value = "账单ID")
    private Long id;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "币种ID")
    private Integer coinId;
    @ApiModelProperty(value = "账单类型")
    private Integer type;
    @ApiModelProperty(value = "可用变化量")
    private BigDecimal availableVariation;
    @ApiModelProperty(value = "冻结变化量")
    private BigDecimal freezeVariation;
    @ApiModelProperty(value = "可用余额")
    private BigDecimal availableBalance;
    @ApiModelProperty(value = "冻结余额")
    private BigDecimal freezeBalance;
    @ApiModelProperty(value = "创建时间")
    private Long createTime;
    @ApiModelProperty(value = "账单说明")
    private String billExplain;
    @ApiModelProperty(value = "备用字段")
    private String alternateField;
    @ApiModelProperty(value = "外部ID")
    private Long otherId;
    @ApiModelProperty(value = "外键ID")
    private Long refied;
    @ApiModelProperty(value = "是否处理")
    private Boolean isDispose;

    public OtcTradeBill() { }

}