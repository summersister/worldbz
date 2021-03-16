package com.richman.domain.c2c.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "挂卖单参数")
public class SaveSellOrderDTO {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;
    @ApiModelProperty(value = "支付密码", required = true)
    private String payPass;
    @ApiModelProperty(value = "币种ID", required = true)
    private Integer coinId;
    @ApiModelProperty(value = "卖出价格", required = true)
    private BigDecimal sellPrice;
    @ApiModelProperty(value = "卖出数量", required = true)
    private BigDecimal sellAmount;
    @ApiModelProperty(value = "最低交易数量", required = true)
    private BigDecimal minQuantity;
    @ApiModelProperty(value = "是否支持银行卡付款")
    private Boolean isBank = false;
    @ApiModelProperty(value = "是否支持支付宝付款")
    private Boolean isAlipay = false;
    @ApiModelProperty(value = "是否支持微信付款")
    private Boolean isWeChat = false;
    @ApiModelProperty(value = "访问密钥")
    private String accessSecret;
    @ApiModelProperty(value = "验证码")
    private String verificationCode;
    @ApiModelProperty(value = "来源")
    private String from;
    @ApiModelProperty(value = "单笔最大购买限制")
    private BigDecimal singleMaxAmountLimit;

}
