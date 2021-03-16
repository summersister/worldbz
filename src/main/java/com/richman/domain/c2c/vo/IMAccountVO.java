package com.richman.domain.c2c.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "IMAccountVO(环信账户视图对象)")
public class IMAccountVO {

    @ApiModelProperty(value = "当前用户环信账号")
    private String currentAccount;

    @ApiModelProperty(value = "当前用户环信密码")
    private String currentPass;

    @ApiModelProperty(value = "对方环信账号")
    private String adverseAccount;

}
