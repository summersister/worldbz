package com.richman.domain.c2c.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "OtcTradeOrderVO(C2C订单视图对象)")
public class OtcTradeOrderVO {

    @ApiModelProperty(value = "订单ID")
    private Integer id;

    @ApiModelProperty(value = "买用户ID")
    private Integer payUserId;

    @ApiModelProperty(value = "卖用户ID")
    private Integer sellUserId;

    @ApiModelProperty(value = "交易类型")
    private Integer tradeType;

    @ApiModelProperty(value = "币种ID")
    private Integer coinId;

    @ApiModelProperty(value = "币种名称")
    private String coinName;

    @ApiModelProperty(value = "交易状态")
    private Integer tradeStatus;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    private String priceStr;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    private String quantityStr;

    @ApiModelProperty(value = "交易总额")
    private BigDecimal amount;

    private String amountStr;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "价格精度")
    private Integer priceLimit;

    @ApiModelProperty(value = "数量精度")
    private Integer amountLimit;

    public String getPriceStr() {
        return this.price == null ? null : this.price.stripTrailingZeros().toPlainString();
    }

    public String getQuantityStr() {
        return this.quantity == null ? null : this.quantity.stripTrailingZeros().toPlainString();
    }

    public String getAmountStr() {
        return this.amount == null ? null : this.amount.stripTrailingZeros().toPlainString();
    }
}
