package com.thinkifu.origin.commons.util;

import cn.hutool.core.util.StrUtil;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期处理
 *
 * @author dell
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 判断年月日是否是未来或者今天
     * @param date
     * @return 是今天或者未来返回true 否则false
     */
	public static boolean dateOfFutureOrToday(LocalDate date) {
        return date.compareTo(LocalDate.now()) > 0;
    }
    
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static String dateTimeToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
        return format.format(date);
    }
    
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        return format.format(date);
    }
    public static String dateToStr(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
    
    public static String dateToStr(Date date, String stringFormat) {
        return new SimpleDateFormat(stringFormat).format(date);
    }
    
    public static String dateToStr(LocalDate date, String stringFormat) {
        return date.format(DateTimeFormatter.ofPattern(stringFormat));
    }
    public static String dateToStr(LocalDateTime date, String stringFormat) {
        return date.format(DateTimeFormatter.ofPattern(stringFormat));
    }
    public static String dateToStr(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    
    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    /**
     * 年月日的转换
     * @param strDate
     * @return
     */
    public static LocalDate stringToDate(String strDate) {
        if (StrUtil.isBlank(strDate)){
            return null;
        }
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN));
    }
    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDateTime(String strDate, String pattern) {
        if (StrUtil.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDateTime.parse(strDate, fmt).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date转localDateTime
     * @param date
     */
    public static LocalDateTime dateConvertLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        LocalDateTime now = LocalDateTime.now();
        Date beginDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(now.plusWeeks(week).atZone(ZoneId.systemDefault()).toInstant());
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusHours(hours).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusWeeks(weeks).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusMonths(months).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.plusYears(years).atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * 获取date是周几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.getDayOfWeek().getValue();
    }
    
}
