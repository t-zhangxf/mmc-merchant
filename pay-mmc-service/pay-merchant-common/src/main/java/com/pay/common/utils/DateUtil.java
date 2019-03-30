package com.pay.common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * @author williams.qian Date Util
 */
public class DateUtil {

	/**
	 * Date Time Format yyyyMMddHHmmssSSS
	 */
	private static final DateTimeFormatter DATE_TIME_LEN17 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	/**
	 * Date Time Format yyyy-MM-dd HH:mm:ss
	 */
	private static final DateTimeFormatter DATE_TIME_LEN22 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	/**
	 * Date Time Format yyyyMMddHHmmss
	 */
	private static final DateTimeFormatter DATE_TIME_LEN14 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	/**
	 * Date Format yyyyMMdd
	 */
	private static final DateTimeFormatter DATE_LEN8 = DateTimeFormatter.ofPattern("yyyyMMdd");
	/**
	 * Date Format yyyy-MM-dd
	 */
	private static final DateTimeFormatter DATE_LEN10 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Time Format HHmmSS
	 */
	private static final DateTimeFormatter TIME_LEN6 = DateTimeFormatter.ofPattern("HHmmSS");

	/**
	 * Time Format HH:mm:SS
	 */
	private static final DateTimeFormatter TIME_LEN8 = DateTimeFormatter.ofPattern("HH:mm:SS");

	/**
	 * Get Now Format Of yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getNow17() {
		return LocalDateTime.now().format(DATE_TIME_LEN17);
	}

	/**
	 * Get Now Format Of yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNow22() {
		return LocalDateTime.now().format(DATE_TIME_LEN22);
	}

	/**
	 * Get Now Format Of yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getNow14() {
		return LocalDateTime.now().format(DATE_TIME_LEN14);
	}

	/**
	 * Get Now Format Of yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateNow10() {
		return LocalDateTime.now().format(DATE_LEN10);
	}

	/**
	 * Get Now Format Of yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDateNow8() {
		return LocalDateTime.now().format(DATE_LEN8);
	}

	/**
	 * Get Now Time Format Of HHmmSS
	 * 
	 * @return
	 */
	public static String getTimeNow6() {
		return LocalDateTime.now().format(TIME_LEN6);
	}

	/**
	 * Get Now Time Format Of HH:mm:SS
	 * 
	 * @return
	 */
	public static String getTimeNow8() {
		return LocalDateTime.now().format(TIME_LEN8);
	}

	/**
	 * Parse Date Time
	 * 
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static LocalDateTime getDateTime(String date, DateTimeFormatter formatter) {
		return LocalDateTime.parse(date, formatter);
	}

	/**
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getDateTimeStr(LocalDateTime date, DateTimeFormatter formatter) {
		return formatter.format(date);
	}

	/**
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getDateTimeStr(Date date, DateTimeFormatter formatter) {
		return formatter.format(date2LocalDateTime(date));
	}

	/**
	 * Date Convert To LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}

	/**
	 * LocalDateTime Convert To Date
	 * 
	 * @param localDateTime
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}

	/**
	 * Add Years
	 *
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	/**
	 * Add Days
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * Add Minutes
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * Get Start of Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * Get End of Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * Get Indian Next Week Start Day
	 * 
	 * @return
	 */
	public static Date getIndianNextWeekStartDay(int next) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.add(Calendar.WEEK_OF_YEAR, next);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Get Indian This Week Start Day
	 * 
	 * @return
	 */
	public static Date getIndianThisWeekStartDay() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Get Indian This Day Start
	 * 
	 * @return
	 */
	public static Date getIndianDayStart(int next) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.add(Calendar.DAY_OF_YEAR, -1 * next);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Get Indian This Day End
	 * 
	 * @return
	 */
	public static Date getIndianDayEnd() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		format.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(format.format(cal.getTime()));
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		String str=format.format(cal.getTime());
		System.out.println(str);
		//format.parse(str,"yyyyMMddHHmmss");
		

	}
}
