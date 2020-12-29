package com.pengniao.cdzcharginginterface.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassNameDateTimeUtil
 * @Description 日期时间工具类
 * @Author
 * @Date2020/8/3 11:43
 * @Version V1.0
 **/
public  class DateTimeUtil {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String DATE_TIME_PATTERN_STRING = "yyyyMMddHHmmss";

    /* 功能描述: 获取当前系统时间30分钟以后的时间
     * @param
    * @return: java.lang.String
    * @Author: home
    * @Date: 2020/8/3 11:45
    */
    public  static String  get30MinutesAfter(){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE,30);
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        return df.format(now.getTime());
    }

    /* 功能描述: 取当前系统时间1分钟以后的时间
     * @param
    * @return: java.lang.String
    * @Author: home
    * @Date: 2020/8/3 11:49
    */
    public  static String  get1MinutesAfter(){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE,1);
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        return df.format(now.getTime());
    }

    /* 功能描述: 获取当前系统时间
     * @param
    * @return: java.lang.String
    * @Author: home
    * @Date: 2020/8/3 15:22
    */
    public  static String getSystemTime(){
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);//设置日期格式
       return df.format(new Date());
    }

    public  static String getSystemTimeString(){
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN_STRING);//设置日期格式
        return df.format(new Date());
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));
        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
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
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减 返回String类型日期
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static String addDateMinutes_Str(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        Date _date = dateTime.plusMinutes(minutes).toDate();
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);//设置日期格式
        return df.format(_date);
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }
}