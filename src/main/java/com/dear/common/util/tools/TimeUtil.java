package com.dear.common.util.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static SimpleDateFormat dateTimeFormatNotMiles = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static SimpleDateFormat dateTimeNotSplitFormat = new SimpleDateFormat("yyyyMMddHHmmss:SSS");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	static SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
	static SimpleDateFormat MMddFormat = new SimpleDateFormat("MM-dd");
	static SimpleDateFormat YYMMddHHmmssFormatSlash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 时间工具精确到毫秒
	 * 
	 * @return
	 */
	public static String getNowDateTime() {
		Date today = new Date();
		return dateTimeFormat.format(today);
	}

	public static String getNowTime() {
		Date today = new Date();
		return timeFormat.format(today);
	}

	public static String getNowDate() {
		Date today = new Date();
		return dateFormat.format(today);
	}

	public static long getUnixTime() {
		return (System.currentTimeMillis() / 1000L);
	}

    /*public static long getUnixTimeMillis() {
        return System.currentTimeMillis();
    }*/

	public static String getFormatData(long ctime) {
		Date today = new Date(ctime * 1000L);
		return YYMMddHHmmssFormatSlash.format(today);
	}

	public static String getFormatDataTwo(long ctime) {
		Date today = new Date(ctime * 1000L);
		return dateFormat.format(today);
	}

	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 判断是否在当前时间内
	 *
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static boolean isInDate(Date date, String start, String end) throws Exception {

		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");

		String format = sd.format(date);

		Date fo = sd.parse(format);
		Date str = sd.parse(start);
		Date en = sd.parse(end);

		if(fo.getTime() >= str.getTime() && fo.getTime() <= en.getTime()){

			return true;

		}else{

			return false;
		}
	}

	/**
	 * 获取今天日期的 0点0分0秒
	 *
	 * @return
	 */
	public static long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(11, 0);
		todayStart.set(12, 0);
		todayStart.set(13, 0);
		todayStart.set(14, 0);
		return (todayStart.getTime().getTime() / 1000L);
	}

	/**
	 * 获取本周开始到现在的时间戳
	 *
	 * [1560700800593, 1560741187593]
	 *
	 * @return
	 */
	public static long[] getThisWeekTimeFrame() {
		Calendar calendar = Calendar.getInstance();
		long[] frames = new long[]{0, 0};
		calendar.set(Calendar.MILLISECOND, 0);
		frames[1] = calendar.getTimeInMillis() / 1000L;
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		frames[0] = calendar.getTimeInMillis() / 1000L;
		return frames;
	}

	/**
	 * 获取上周开始到结束的时间戳
	 *
	 * [1560096000000, 1560700800000]
	 * @return
	 */
	public static long[] getLastWeekTimeFrame() {
		Calendar calendar = Calendar.getInstance();
		long[] frames = new long[]{0, 0};
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		frames[1] = calendar.getTimeInMillis() / 1000L;
		calendar.add(Calendar.DAY_OF_WEEK, -7);
		frames[0] = calendar.getTimeInMillis() / 1000L;
		return frames;
	}

	/**
	 * 获取当前时间的第二天凌晨 2019-07-23 00:00:00
	 *
	 * @param time
	 * @return
	 */
	public static Long getTheNextMorning(Long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(time * 1000));
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis() / 1000;
	}


}