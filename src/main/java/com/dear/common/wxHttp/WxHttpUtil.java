package com.dear.common.wxHttp;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 三方请求工具类
 */
@Slf4j
@Component
public class WxHttpUtil {

    @Autowired
    private WxConfig config;

    @Autowired
    private WxUtil util;

    @Autowired
    private RestTemplate rest;

    /**
     * 获取登录codfe2Session
     *
     * @param code
     * @return
     */
    public String getCodeSession(String code) {

        JSONObject jo = null;

        SortedMap<Object, Object> map = new TreeMap();

        map.put("appid", this.config.appId);
        map.put("secret", this.config.secret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        try {

            jo = this.rest.getForObject(
                    this.util.createGetData(WxConfig.CODE_TWO_SESSION, map),
                    JSONObject.class);

        } catch (Exception e) {

            log.error("调用接口 异常 " + e.getMessage());
            e.printStackTrace();
        }

        if(jo != null) {

            String openid = jo.getString("openid");

            if(StringUtils.isNotBlank(openid)) {

                return openid;
            }
        }

        return null;
    }

    /**
     * 获取用户UnionId
     *
     * @param openId
     * @param accessToken
     * @return
     */
    public String getPaidUnionId(String openId, String accessToken) {

        JSONObject jo = null;

        SortedMap<Object, Object> map = new TreeMap();

        map.put("access_token", accessToken);
        map.put("openid", openId);

        try {

            jo = this.rest.getForObject(
                    this.util.createGetData(WxConfig.GET_PAID_UNION_ID, map),
                    JSONObject.class);

        } catch (Exception e) {

            log.error("调用接口 异常 " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println(jo.toJSONString());

        if(jo != null) {

            String unionId = jo.getString("unionid");

            if(StringUtils.isNotBlank(unionId)) {

                return unionId;
            }
        }

        return null;
    }

    public String getToken() {

        JSONObject jo = null;

        SortedMap<Object, Object> map = new TreeMap();

        map.put("grant_type", "client_credential");
        map.put("appid", this.config.appId);
        map.put("secret", this.config.secret);

        try {

            jo = this.rest.getForObject(
                    this.util.createGetData(WxConfig.AUTH_TOKEN, map),
                    JSONObject.class);

        } catch (Exception e) {

            log.error("调用接口 异常 " + e.getMessage());
            e.printStackTrace();
        }

        if(jo != null) {

            String accessToken = jo.getString("access_token");

            if(StringUtils.isNotBlank(accessToken)) {

                return accessToken;
            }
        }

        return null;
    }

}

