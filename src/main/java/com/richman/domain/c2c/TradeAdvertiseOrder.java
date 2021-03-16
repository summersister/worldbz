package com.richman.domain.c2c;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "广告单")
public class TradeAdvertiseOrder {

    @ApiModelProperty(value = "广告单ID")
    private Long id;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "交易币种")
    private Integer coinId;
    @ApiModelProperty(value = "交易类型(1买；2卖)")
    private Integer tradeType;
    @ApiModelProperty(value = "商家手续费费率")
    private BigDecimal poundage;
    @ApiModelProperty(value = "用户手续费费率")
    private BigDecimal userPoundage;
    @ApiModelProperty(value = "额外冻结手续费")
    private BigDecimal extraFrozen;
    @ApiModelProperty(value = "是否支持银行卡付款")
    private Boolean isBank;
    @ApiModelProperty(value = "是否支持支付宝付款")
    private Boolean isAlipay;
    @ApiModelProperty(value = "是否支持微信付款")
    private Boolean isWeChat;
    @ApiModelProperty(value = "交易状态 1.上架,2.下架,3.隐藏")
    private Integer status;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "挂单数量")
    private BigDecimal orderQuantity;
    @ApiModelProperty(value = "最低交易数量")
    private BigDecimal minQuantity;
    @ApiModelProperty(value = "待处理数量")
    private BigDecimal waitDisposeQuantity;
    @ApiModelProperty(value = "已成交数量")
    private BigDecimal finishQuantity;
    @ApiModelProperty(value = "挂单时间")
    private Long createTime;
    @ApiModelProperty(value = "下架时间")
    private Long soldOutTime;
    @ApiModelProperty(value = "下架备注")
    private String soldOutRemark;
    @ApiModelProperty(value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(value = "是否隐藏 字段废弃,使用status=3表示隐藏")
    private Boolean isHide;
    @ApiModelProperty("单笔最大购买限制")
    private BigDecimal singleMaxAmountLimit;
    private String singleMaxAmountLimitStr;

    public String getSingleMaxAmountLimitStr() {
        return StrUtil.toString(this.singleMaxAmountLimit);
    }

}