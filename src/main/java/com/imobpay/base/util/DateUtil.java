package com.imobpay.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间处理工具类 ClassName: DateUtil <br/>
 * date: 2016年6月1日 下午4:26:02 <br/>
 * 
 * @author Lance.WU
 * @since JDK 1.6 platform 1.0
 */
public final class DateUtil {

    /** 日期时间格式  */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** 日期 格式*/
    public static final String YYYYMMDD = "yyyyMMdd";

    /** 时间格式 */
    public static final String HHMMSS = "HHmmss";

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：yyyyMMdd.<br/>
     * 
     * @author Lance.Wu <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * 
     * @author Lance.Wu <br/>
     * @param format
     *            自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * 
     * @author Lance.Wu <br/>
     * @param l
     *            时间格式 <br/>
     * @param format
     *            自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(long l, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(l));
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * 
     * @author Lance.Wu <br/>
     * @param date
     *            时间 <br/>
     * @param format
     *            自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：yyyyMMdd.<br/>
     * 
     * @author Lance.Wu <br/>
     * @param dateStr
     *            时间字符串 <br/>
     * @return 返回结果：Date <br/>
     * @throws ParseException
     *             转换异常 <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date getCurrDateStr(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.parse(dateStr);
    }

    /**
     * getCurrTime:(获取当前日期). <br/>
     * 格式为：hhMMss.<br/>
     * 
     * @author Lance.Wu <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrTime() {
        DateFormat df = new SimpleDateFormat("hhMMss");
        return df.format(new Date());
    }

    /**
     * getCurrTime:(获取当前日期). <br/>
     * 格式为：hhMMss.<br/>
     * 
     * @author Lance.Wu <br/>
     * @param format
     *            格式化 <br/>
     * @return 返回结果：String <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrTime(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * addDate:(计算时间格式). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param date
     *            时间 <br/>
     * @param num
     *            基数 <br/>
     * @param calendarType
     *            时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date addDate(Date date, int num, int calendarType) {
        Calendar sc = Calendar.getInstance();
        sc.setTime(date);
        sc.add(calendarType, num);
        return sc.getTime();
    }

    /**
     * addDate:(计算时间格式). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param date
     *            时间 <br/>
     * @param num
     *            基数 <br/>
     * @param calendarType
     *            时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @throws ParseException
     *             转换失败
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date addDate(String date, int num, int calendarType) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Calendar sc = Calendar.getInstance();
        sc.setTime(sd.parse(date));
        sc.add(calendarType, num);
        return sc.getTime();
    }

    /**
     * addDate:(计算时间格式). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param date
     *            时间 <br/>
     * @param num
     *            基数 <br/>
     * @param calendarType
     *            时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @param formart
     *            返回内容格式 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String addDate(Date date, int num, int calendarType, String formart) {
        Calendar sc = Calendar.getInstance();
        sc.setTime(date);
        sc.add(calendarType, num);
        SimpleDateFormat sd = new SimpleDateFormat(formart);
        return sd.format(sd);
    }

}
