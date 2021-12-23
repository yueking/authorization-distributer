package com.yueking.core.id.utils;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Created by company on 2016-07-05</p>
 * <p>Copyright: Copyright (c) 2016   </p>
 */
public class DateUtil {
    static public String getYear() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }

    static public String getYearMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }

    static public String getYearMonthDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }

    static public String getDataStringFormPattern(String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }


    /**
     * 日志
     */
    private static Logger log = Logger.getLogger(DateUtil.class);

    /**
     * 日期格式
     */
    private static String datePattern = "yyyy-MM-dd";

    /**
     * 时间格式
     */
    private static String timePattern = "HH:mm:ss";

    /**
     * 日期时间格式
     */
    private static String datetimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 中文日期格式
     */
    private static String datePattern_CN = "yyyy年M月d日";

    /**
     * 中文日期时间格式 精确到分
     */
    private static String datetimePattern_CN = "yyyy年M月d日H时m分";


    // ~ Methods
    // ================================================================

    /**
     * Return default datePattern (yyyy-MM-dd)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * This method attempts to convert an Oracle-formatted date in the form
     * dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     *
     * @param datePattern   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException 转换异常
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String strDate,String datePattern) throws ParseException {
        SimpleDateFormat dateFormat = null;
        Date date = null;
        dateFormat = new SimpleDateFormat(datePattern);

        log.debug("converting '" + strDate + "' to date with datePattern '" + datePattern + "'");

        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return date;
    }

    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     *
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException 转换异常
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDateTime(String strDate) throws ParseException {
        return DateUtil.convertStringToDate(strDate,datetimePattern);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(theTime,timePattern);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException 转换异常
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param datePattern the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static final String getDateTime(Date aDate,String datePattern) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return returnValue;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static final String getDateTime(Date aDate) {
        return DateUtil.getDateTime(aDate, datetimePattern);
    }

    /**
     * This method generates a string representation of a date based on the
     * SystemService Property 'dateFormat' in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(aDate,datePattern);
    }

    /**
     * This method generates a string representation of a date based on the
     * SystemService Property 'dateFormat' in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate,String datePattern) {
        return getDateTime(aDate,datePattern);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException 转换异常
     */
    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }

            aDate = convertStringToDate(strDate,datePattern);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());

        }

        return aDate;
    }

    /**
     * 如果date1>date2 返回 1 = 0 < -1
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 比较结果
     */
    public static int compareDate(Date date1, Date date2) {
        String d1 = getDateTime(date1,datePattern);
        String d2 = getDateTime(date2,datePattern);

        if (d1 == null && d2 != null)
            return -1;
        else if (d1 != null && d2 == null)
            return 1;
        else if (d1 == null && d2 == null)
            return 0;
        else
            return d1.compareTo(d2);
    }

    public static String convertDatetimeToChineseString(Date date) {
        DateFormat df = new SimpleDateFormat(datetimePattern_CN);
        String strDate = df.format(date);
        return strDate;
    }

    public static String convertDateToChineseString(Date date) {
        DateFormat df = new SimpleDateFormat(datePattern_CN);
        String strDate = df.format(date);
        return strDate;
    }


    /**
     * 计算2个时间的差
     *
     * @param endDate
     * @param beginDate
     * @return
     */
    public static Integer getUnm(Date endDate, Date beginDate) {
        Integer a = 0;
        try {
            Long days = (endDate.getTime() - beginDate.getTime())
                    / (24 * 60 * 60 * 1000) + 1;
            a = days.intValue();
        } catch (Exception e) {

            e.printStackTrace();
            a = 0;
        }
        return a;
    }

    /**
     * string 转 date yyyy-dd-mm
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {

            dt = sdf.parse(time);
            //SystemService.out.print(sdf.format(dt));     //输出结果是：2005-2-19
        } catch (Exception ex) {

        }
        return dt;
    }

    public static void main(String[] args) {
        String a = convertDatetimeToChineseString(new Date());

        System.out.println(a);
        System.out.println(Calendar.getInstance().getWeekYear());
    }
}
