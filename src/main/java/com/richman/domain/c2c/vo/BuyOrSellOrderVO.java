package com.richman.domain.c2c.vo;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "BuyOrSellOrderVO(买卖单视图对象)")
public class BuyOrSellOrderVO {

    @ApiModelProperty(value = "广告单ID")
    private Integer id;

    @ApiModelProperty("币种id")
    private Integer coinId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    private String priceStr;

    @ApiModelProperty(value = "最低交易数量")
    private BigDecimal minQuantity;
    private String minQuantityStr;

    @ApiModelProperty(value = "挂单数量")
    private BigDecimal orderQuantity;
    private String orderQuantityStr;

    @ApiModelProperty(value = "处理中数量")
    private BigDecimal waitDisposeQuantity;
    private String waitDisposeQuantityStr;

    @ApiModelProperty(value = "已完成数量")
    private BigDecimal finishQuantity;
    private String finishQuantityStr;

    @ApiModelProperty(value = "是否支持银行卡付款")
    private Boolean isBank;

    @ApiModelProperty(value = "是否支持支付宝付款")
    private Boolean isAlipay;

    @ApiModelProperty(value = "是否支持微信付款")
    private Boolean isWeChat;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "成交单数")
    private Integer successCount;

    @ApiModelProperty(value = "失败单数")
    private Integer failedCount;

    @ApiModelProperty(value = "平均用时(分钟)")
    private Double averageTime;

    @ApiModelProperty("单笔最大购买限制")
    private BigDecimal singleMaxAmountLimit;
    private String singleMaxAmountLimitStr;

    @ApiModelProperty("成交量")
    private Integer volume;

    @ApiModelProperty("成交率")
    private String volumeRate;

    @ApiModelProperty("价格精度 总价病毒")
    private int priceLimit;

    @ApiModelProperty("数量精度")
    private int amountLimit;

    public String getSingleMaxAmountLimitStr() {
        return StrUtil.toString(this.singleMaxAmountLimit);
    }

    public String getPriceStr() {
        return this.price == null ? null : this.price.stripTrailingZeros().toPlainString();
    }

    public String getMinQuantityStr() {
        return this.minQuantity == null ? null : this.minQuantity.stripTrailingZeros().toPlainString();
    }

    public String getOrderQuantityStr() {
        return this.orderQuantity == null ? null : this.orderQuantity.stripTrailingZeros().toPlainString();
    }

    public String getWaitDisposeQuantityStr() {
        return this.waitDisposeQuantity == null ? null : this.waitDisposeQuantity.stripTrailingZeros().toPlainString();
    }

    public String getFinishQuantityStr() {
        return this.finishQuantity == null ? null : this.finishQuantity.stripTrailingZeros().toPlainString();
    }
}
