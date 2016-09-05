package com.hydra.tool.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZhengGong on 15/3/16.
 * Description 时间操作工具类
 */
public class DateUtil {
    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);//属性很多也有月等等，可以操作各种时间日期
        return c.getTime();
    }

    public static String formatToSecond(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public static String formatToDay(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public static String formatToMinute(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmm");
        return format.format(date);
    }

    public static String formatToMonth(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMM");
        return format.format(date);
    }

    public static String formatToHour(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHH");
        return format.format(date);
    }

    public static String formatToYear(Date date) {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static Date stringSecondToDate(String str) throws ParseException {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        return format.parse(str);
    }

    public static Date stringDateToDate(String str) throws ParseException {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
        return format.parse(str);
    }

    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);//属性很多也有月等等，可以操作各种时间日期
        return c.getTime();
    }
}
