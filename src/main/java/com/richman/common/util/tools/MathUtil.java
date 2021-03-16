package com.richman.common.util.tools;

import java.math.BigDecimal;

public class MathUtil {
	/**
	 * @info:加法运算
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
	}

	/**
	 * @info:减法运算
	 */
	public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}

	/**
	 * @info:乘法运算
	 */
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * @info:除法运算 除不尽保留
	 */
	public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {
		// 默认采用保留10位小数
		int DEF_DIV_SCALE = 10;
		return v1.divide(v2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * @info:除法运算 除不尽保留
	 */
	public static BigDecimal divideBig(BigDecimal v1, BigDecimal v2) {
		// 默认采用保留10位小数
		int DEF_DIV_SCALE = 10;
		return v1.divide(v2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * @info:精度运算 :四舍五入
	 */
	public static BigDecimal accuracyRoundHalfUp(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	
	/**
	 * @info:精度运算 :直接舍去
	 */
	public static BigDecimal accuracyRoundDown(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_DOWN);
	}

}
