package com.richman.domain.c2c.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "收款方式参数")
public class SaveGatherWayDTO {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "主键ID", required = false)
    private Integer id;

    @ApiModelProperty(value = "收款账户", required = false)
    private String gatherAccount;

    @ApiModelProperty(value = "收款账户姓名", hidden = true)
    private String realName;

    @ApiModelProperty(value = "收款方式(1银行卡；2支付宝；3微信)", required = false)
    private Integer gatherWay;

    @ApiModelProperty(value = "银行名称(限银行卡支付)", required = false)
    private String bankName;

    @ApiModelProperty(value = "银行所在地址(限银行卡支付)", required = false)
    private String bankAddress;

    @ApiModelProperty(value = "开户行名称(限银行卡支付)", required = false)
    private String bankOpen;

    @ApiModelProperty(value = "是否删除(0未删除；1删除)", required = true)
    private Boolean delTag;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "支付宝二维码地址", required = false)
    private String alipayQRAddress;

    @ApiModelProperty(value = "微信二维码地址", required = false)
    private String weChatQRAddress;

    @ApiModelProperty(value = "手机号码", required = false)
    private String mobile;

    @ApiModelProperty(value = "邮箱", required = false)
    private String email;

    @ApiModelProperty(value = "银行卡开关", required = false)
    private Boolean bankSwitch;

}
