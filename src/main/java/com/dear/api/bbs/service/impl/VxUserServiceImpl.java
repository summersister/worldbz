package com.dear.api.bbs.service.impl;

import com.dear.api.bbs.service.IWxUserService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.common.wxHttp.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VxUserServiceImpl implements IWxUserService {

    @Autowired
    private WxHttpUtil wxHttpUtil;

    @Override
    public ResultJson onLogin(String code) {

        String openId = this.wxHttpUtil.getCodeSession(code);



        return new ResultJson(ResultCode.ERROR.getCode(), null);
    }
}
