package com.richman.domain.c2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单状态
 * 1 - 待打款
 * 2 - 待放币
 * 3 - 已放币
 * 4 - 人工介入
 * 5 - 介入放币
 * 6 - 介入退币
 * 7 - 仲裁中
 * 8 - 仲裁放币
 * 9 - 仲裁退币
 * 10 - 取消
 */
@Data
@ApiModel(value = "交易订单")
public class TradeOrderInfo {
    @ApiModelProperty(value = "订单ID")
    private Long id;
    @ApiModelProperty(value = "买方用户ID")
    private Integer payUserId;
    @ApiModelProperty(value = "买方姓名")
    private String payUserName;
    @ApiModelProperty(value = "买方聊天账户")
    private String payChatAccount;
    @ApiModelProperty(value = "买方聊天密码")
    private String payChatPass;
    @ApiModelProperty(value = "卖方用户ID")
    private Integer sellUserId;
    @ApiModelProperty(value = "卖方姓名")
    private String sellUserName;
    @ApiModelProperty(value = "卖方聊天账户")
    private String sellChatAccount;
    @ApiModelProperty(value = "卖方聊天密码")
    private String sellChatPass;
    @ApiModelProperty(value = "交易币种")
    private Integer coinId;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    private String priceStr;
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    private String quantityStr;
    @ApiModelProperty(value = "交易总额")
    private BigDecimal amount;
    private String amountStr;
    @ApiModelProperty(value = "商家手续费")
    private BigDecimal poundage;
    private String poundageStr;
    @ApiModelProperty(value = "普通用户手续费")
    private BigDecimal userPoundage;
    private String userPoundageStr;
    @ApiModelProperty(value = "创建时间")
    private Long createTime;
    @ApiModelProperty(value = "银行卡号")
    private String bankNo;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "开户行")
    private String bankOpen;
    @ApiModelProperty(value = "开户人姓名")
    private String bankRealName;
    @ApiModelProperty(value = "支付宝账户")
    private String alipayAccount;
    @ApiModelProperty(value = "支付宝开户人姓名")
    private String alipayOpenName;
    @ApiModelProperty(value = "支付宝收款二维码路径")
    private String alipayQrPath;
    @ApiModelProperty(value = "微信账户")
    private String weChatAccount;
    @ApiModelProperty(value = "微信开户人姓名")
    private String weChatOpenName;
    @ApiModelProperty(value = "微信收款二维码路径")
    private String weChatQrPath;
    @ApiModelProperty(value = "付款时间")
    private Long paymentTime;
    @ApiModelProperty(value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(value = "交易状态")
    private Integer status;
    @ApiModelProperty(value = "处理人员（id）")
    private Integer disposer;
    @ApiModelProperty(value = "进入仲裁时间")
    private Long arbitrationTime;
    @ApiModelProperty(value = "过错方（id）")
    private String mistakeId;
    @ApiModelProperty(value = "过错方姓名")
    private String mistakeName;
    @ApiModelProperty(value = "处理备注")
    private String disposeRemark;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "所属广告单")
    private Long advertiseId;
    @ApiModelProperty(value = "服务器当前时间")
    private long currentTime;
    @ApiModelProperty("支付方式")
    private String paymentMethod;

    public String getPriceStr() {
        return this.price == null ? null : this.price.stripTrailingZeros().toPlainString();
    }

    public String getQuantityStr() {
        return this.quantity == null ? null : this.quantity.stripTrailingZeros().toPlainString();
    }

    public String getAmountStr() {
        return this.amount == null ? null : this.amount.stripTrailingZeros().toPlainString();
    }

    public String getPoundageStr() {
        return this.poundage == null ? null : this.poundage.stripTrailingZeros().toPlainString();
    }

    public String getUserPoundageStr() {
        return this.userPoundage == null ? null : this.userPoundage.stripTrailingZeros().toPlainString();
    }
}