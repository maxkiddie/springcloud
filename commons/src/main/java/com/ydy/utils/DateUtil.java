/**
 * 
 */
package com.ydy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuzhaojie
 *
 *         2018年9月20日 上午10:24:20
 */
public class DateUtil {
	private final static Logger log = LoggerFactory.getLogger(DateUtil.class);

	public final static String pattern_id = "yyyyMMddHHmmssSSS";
	public final static String pattern_full = "yyyy-MM-dd HH:mm:ss";
	public final static String pattern_date = "yyyy-MM-dd";
	public final static String pattern_time = "HH:mm:ss";
	public final static String pattern_file_date = "yyyy年MM月dd日HH时mm分ss秒";

	public final static Long ONE_DAY_MILLONS = 24 * 60 * 60 * 1000L;
	public final static Long ONE_HOUR_MILLONS = 60 * 60 * 1000L;
	public final static Long ONE_MINUTE_MILLONS = 60 * 1000L;
	public final static Long ONE_SECOND_MILLONS = 1000L;

	private static final ThreadLocal<DateFormat> df_id = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_id);// 常用的一种类型
		}
	};
	private static final ThreadLocal<DateFormat> df_full = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_full);// 常用的一种类型
		}
	};

	private static final ThreadLocal<DateFormat> df_date = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_date);// 常用的一种类型
		}
	};
	private static final ThreadLocal<DateFormat> df_time = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_time);// 常用的一种类型
		}
	};

	private static final ThreadLocal<DateFormat> df_file_date = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_file_date);// 常用的一种类型
		}
	};

	private static final DateFormat getDateFormat(ThreadLocal<DateFormat> format) {
		return (DateFormat) format.get();
	}

	public static String produceId() {
		return df_id.get().format(new Date());
	}

	public static Date parseDate(String time) {
		if (time == null && !"".equals(time)) {
			return null;
		}
		try {
			return df_id.get().parse(time);
		} catch (ParseException e) {
		}
		return null;
	}

	// 转化数据类型,string->date
	public static Date getDateByDateFormat(String date_str, DateFormat df) {
		if (StringUtil.isEmpty(date_str))
			return null;
		try {
			// 转化数据格式
			return df.parse(date_str);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	// date->string
	public static String getDateStrByDateFormat(Date date, DateFormat df) {
		return df.format(date);
	}

	public static String getFullDateStr(Date date) {
		if (date == null)
			return "";
		return df_full.get().format(date);
	}

	// 获取当前Date
	public static Date getCurrentDate() {
		Instant instant = Instant.now();
		return Date.from(instant);
	}

	// 获取DateStr[yyyy-MM-dd HH:mm:ss]
	public static String getCurrentTimeFullStr() {
		return getDateStrByDateFormat(getCurrentDate(), getDateFormat(df_full));
	}

	public static String getFileCurrentTime() {
		return getDateStrByDateFormat(getCurrentDate(), getDateFormat(df_file_date));
	}

	public static String getTodatDate() {
		DateFormat df = df_date.get();
		return df.format(new Date());
	}

	public static String getDateTime(Date date) {
		if (date == null)
			return "";
		DateFormat df = df_time.get();
		return df.format(date);
	}

	public static String getDateFile(Date date) {
		if (date == null)
			return "";
		DateFormat df = df_date.get();
		return df.format(date);
	}

	public static Boolean between(Date startTime, Date endTime) {
		if (startTime == null || endTime == null)
			throw new NullPointerException();
		Date now = new Date();
		if (now.after(startTime) && now.before(endTime))
			return true;
		return false;

	}

	public static Date getBeforeOrAfterDayZeroTime(Integer days) {
		long cur = System.currentTimeMillis() + (days * ONE_DAY_MILLONS);
		cur = cur - (cur / ONE_DAY_MILLONS);
		return new Date(cur);
	}

	public static Date getBeforeOrAfterDayCurTime(Date date, Integer days) {
		long cur = date.getTime() + (days * ONE_DAY_MILLONS);
		return new Date(cur);
	}

	public static Integer betweenDays(Date before, Date after) {
		long cur = Math.abs(createTime(before, 0, 0, 0).getTime() - createTime(after, 0, 0, 0).getTime());
		return Integer.parseInt((cur / ONE_DAY_MILLONS) + "");
	}

	public static Integer getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static Integer getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Integer getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static Integer getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static Integer getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	public static Date getDateStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long today = c.getTimeInMillis();
		return new Date(today);
	}

	public static Date createTime(Integer year, Integer month, Integer day, Integer hour, Integer minute,
			Integer second) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, 0);
		return new Date(c.getTimeInMillis());
	}

	public static Date createTime(Date date, Integer hour, Integer minute, Integer second) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, 0);
		return new Date(c.getTimeInMillis());
	}

	public static boolean isSameToday(Date aTime, Date bTime) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(aTime);
		Calendar cb = Calendar.getInstance();
		cb.setTime(bTime);
		if (!Objects.equals(ca.get(Calendar.DAY_OF_MONTH), cb.get(Calendar.DAY_OF_MONTH))) {
			return false;
		}
		if (!Objects.equals(ca.get(Calendar.MONTH), cb.get(Calendar.MONTH))) {
			return false;
		}
		if (!Objects.equals(ca.get(Calendar.YEAR), cb.get(Calendar.YEAR))) {
			return false;
		}
		return true;
	}

	public static Date getDateTomorrowStartTime(Date date) {
		Long d = getDateStartTime(date).getTime() + ONE_DAY_MILLONS;
		return new Date(d);
	}

	public static Date getPastOrFutureDate(Integer days) {
		return new Date(new Date().getTime() + (days * ONE_DAY_MILLONS));
	}

	public static Date getDayStart(Date date) {
		return createTime(date, 0, 0, 0);
	}

	public static Date getDayEnd(Date date) {
		return createTime(date, 23, 59, 59);
	}

	public static String timeToString(Integer hour, Integer minute) {
		StringBuilder builder = new StringBuilder();
		if (hour < 10) {
			builder.append("0" + hour);
		} else {
			builder.append(hour);
		}
		if (minute < 10) {
			builder.append("0" + minute);
		} else {
			builder.append(minute);
		}
		return builder.toString();
	}

	public static long formatTimeLong(String fmt) {
		if (fmt == null || "".equals(fmt)) {
			throw new NullPointerException("时间格式为空");
		}
		fmt = fmt.toLowerCase();
		long timeLong = 0;
		char c = '0';
		StringBuilder builder = null;
		for (int i = 0; i < fmt.length(); i++) {
			c = fmt.charAt(i);
			if (c >= '0' && c <= '9') {
				if (builder == null)
					builder = new StringBuilder();
				builder.append(c);
			} else if (c == 'd' || c == 'h' || c == 'm' || c == 's') {
				long num = Long.parseLong(builder.toString());
				builder = null;
				switch (c) {
				case 'd':
					timeLong = timeLong + (num * 24 * 60 * 60 * 1000L);
					break;
				case 'h':
					timeLong = timeLong + (num * 60 * 60 * 1000L);
					break;
				case 'm':
					timeLong = timeLong + (num * 60 * 1000L);
					break;
				case 's':
					timeLong = timeLong + (num * 1000L);
					break;
				}
			}
		}
		return timeLong;
	}

	public static String spendTime(Date startTime, Date endTime) {
		if (startTime == null || endTime == null) {
			throw new NullPointerException("时间为空");
		}
		long timeLong = Math.abs(startTime.getTime() - endTime.getTime());
		StringBuilder builder = new StringBuilder();
		Long day = timeLong / ONE_DAY_MILLONS;
		timeLong = timeLong % ONE_DAY_MILLONS;
		Long hour = timeLong / ONE_HOUR_MILLONS;
		timeLong = timeLong % ONE_HOUR_MILLONS;
		Long minute = timeLong / ONE_MINUTE_MILLONS;
		timeLong = timeLong % ONE_MINUTE_MILLONS;
		if (day > 0) {
			builder.append(day).append("d");
		}
		builder.append(hour).append("h");
		builder.append(minute).append("m");
		return builder.toString();
	}

	public static String spendChineseTime(Date startTime, Date endTime) {
		if (startTime == null || endTime == null) {
			throw new NullPointerException("时间为空");
		}
		long timeLong = Math.abs(startTime.getTime() - endTime.getTime());
		StringBuilder builder = new StringBuilder();
		Long day = timeLong / ONE_DAY_MILLONS;
		timeLong = timeLong % ONE_DAY_MILLONS;
		Long hour = timeLong / ONE_HOUR_MILLONS;
		timeLong = timeLong % ONE_HOUR_MILLONS;
		Long minute = timeLong / ONE_MINUTE_MILLONS;
		timeLong = timeLong % ONE_MINUTE_MILLONS;
		Long second = timeLong / ONE_SECOND_MILLONS;

		if (day > 0) {
			builder.append(day).append("天");
		}
		if (hour > 0) {
			builder.append(hour).append("时");
		}
		if (minute > 0) {
			builder.append(minute).append("分");
		}
		builder.append(second).append("秒");
		return builder.toString();
	}

}
