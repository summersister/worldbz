package com.richman.mapper.c2c;


import com.richman.domain.c2c.TradeOrderInfo;
import com.richman.domain.c2c.vo.BuyOrSellOrderVO;
import com.richman.domain.c2c.vo.OtcTradeOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TradeOrderInfoMapper {


    BigDecimal sumTradeAmount(@Param("payUserId") Integer payUserId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

    /**
     * 根据用户和状态查询交易订单的数量 (count)
     *
     * @param userId 用户id
     * @param disposeOrHistory true 处理中 false 历史订单
     * @return
     */
    int getTradeOrderCountByUserAndStatus(
            @Param("userId") Integer userId, @Param("disposeOrHistory") Boolean disposeOrHistory);

    /**
     * 根据用户和状态码查询用户交易订单
     *
     * @return
     */
    List<OtcTradeOrderVO> listMyTradeOrderByStatus(@Param("userId") Integer userId,
                                                   @Param("coinId") Integer coinId,
                                                   @Param("statuses") String[] statuses);

    /**
     * 查询所有买/卖单
     *
     * 注:排序方式
     * tradeType = 1 买单降序（商家买，其他用户卖，价格高靠前）
     * tradeType = 2 卖单升序（商家卖，其他用户买，价格低靠前）
     * tradeType = null 价格一致按成交单数降序（成交量高靠前）
     *
     * @param coinId 币种id
     * @param tradeType 交易方式(1:买; 2:卖; null 所有)
     * @param isNeglectHistory 交易状态(true投放中；false已下架)
     * @return
     */
    List<BuyOrSellOrderVO> listPayOrSellOrderV2(@Param("coinId") Integer coinId,
                                                @Param("tradeType") Integer tradeType,
                                                @Param("isNeglectHistory") Boolean isNeglectHistory);

    /**
     * 根据主键查询交易订单
     *
     * @param id 主键
     * @return 返回交易订单对象
     */
    TradeOrderInfo getTradeOrderInfoById(@Param("id") Long id);

    /**
     * 修改订单为仲裁状态
     * 返回影响行数
     *
     * @param id
     * @return
     */
    int updateTradeOrderIsArbitration(@Param("id") Long id);

    /**
     * 取消交易订单
     *
     * @param userId
     * @param id
     * @param remark
     * @return
     */
    int updateCancelOrder(@Param("userId") Integer userId, @Param("id") Long id, @Param("remark") String remark);

    /**
     * 查询某用户三天内取消的订单数
     *
     * @param userId
     * @return
     */
    int getWithinThreeDaysCancelOrder(@Param("userId") Integer userId);

    /**
     * 保存交易订单
     *
     * @param orderInfo 交易订单对象
     * @return 返回受影响行数
     */
    int saveTradeOrderInfo(TradeOrderInfo orderInfo);

    /**
     * 订单已放币时更新交易订单为确认放币
     *
     * @param id
     * @return
     */
    int updateOrderIsAffirmOutCoin(@Param("id") Long id);

    /**
     * 订单未放币时更新交易订单为确认放币
     *
     * @param id
     * @return
     */
    int updateOrderIsAffirmOutCoinNot(@Param("id") Long id);

    /**
     * 修改交易订单为确认付款
     *
     * @param id 订单id
     * @param userId 买方用户ID
     * @return 返回受影响行数
     */
    int updateOrderIsAffirmPayment(@Param("id") Long id, @Param("userId") Integer userId, @Param("paymentMethod") String paymentMethod);
}