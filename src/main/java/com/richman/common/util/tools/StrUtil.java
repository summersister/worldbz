package com.richman.common.util.tools;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 操作BigDecimal相关工具类
 */
public class StrUtil {

    /**
     * BigDecimal 类型字段 改为非科学计数法
     *
     * @param bigDecimal
     * @return
     */
    public static String toString(BigDecimal bigDecimal){

        return bigDecimal == null ? null : bigDecimal.stripTrailingZeros().toPlainString();
    }

    /**
     * String 类型字段 改为非科学计数法 并截断8位
     *
     * @param str
     * @return
     */
    public static String toString(String str){

        return StringUtils.isBlank(str) ? "0" : MathUtil.accuracyRoundDown(new BigDecimal(str), 8)
                .stripTrailingZeros().toPlainString();
    }

    /**
     * String 类型字段 改为非科学计数法 并截断8位 返回BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str){

        return StringUtils.isBlank(str) ? BigDecimal.ZERO : MathUtil.accuracyRoundDown(new BigDecimal(str), 8);
    }

    /**
     * BigDecimal 类型字段 转为负数 返回 BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal toMinus(BigDecimal str) {

        return str.multiply(new BigDecimal("-1"));
    }

    /**
     * BigDecimal 类型字段 砍8位 返回 BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal cut(BigDecimal str) {

        return MathUtil.accuracyRoundDown(str, 8);
    }

    /**
     * BigDecimal 类型字段 砍n位 返回 BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal cut(BigDecimal str, int scale) {

        return MathUtil.accuracyRoundDown(str, scale);
    }

    /**
     * 符合交易所逻辑的除法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        return v1.divide(v2, scale, BigDecimal.ROUND_DOWN);
    }
}
