package com.richman.api.test.service.impl;

import com.richman.api.test.service.ITestExceptionService;
import com.richman.common.annotaion.MultipleTransaction;
import com.richman.common.bean.ResultCode;
import com.richman.common.bean.ResultJson;
import com.richman.domain.c2c.TradeOrderInfo;
import com.richman.mapper.c2c.TradeOrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestExceptionServiceImpl implements ITestExceptionService {

    @Autowired
    private TradeOrderInfoMapper tradeOrderInfoMapper;

    @Override
    @MultipleTransaction({"commonTM", "userTM"})
    public ResultJson testJackAddEnt() {

        TradeOrderInfo tradeOrderInfoById = this.tradeOrderInfoMapper.getTradeOrderInfoById(1L);

        return new ResultJson(ResultCode.ERROR.getCode(), tradeOrderInfoById);
    }
}
