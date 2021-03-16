package com.dear.api.bbs.service;

import com.dear.common.bean.ResultJson;

public interface IWxUserService {

    ResultJson onLogin(String code);
}
