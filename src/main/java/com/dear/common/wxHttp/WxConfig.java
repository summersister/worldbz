package com.dear.common.wxHttp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WxConfig {

    @Value("${wx.appid}")
    public String appId;

    @Value("${wx.secret}")
    public String secret;

    public static final String CODE_TWO_SESSION = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String GET_PAID_UNION_ID = "https://api.weixin.qq.com/wxa/getpaidunionid";
    public static final String AUTH_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

}

