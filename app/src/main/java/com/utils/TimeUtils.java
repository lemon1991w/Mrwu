package com.utils;

import android.content.Context;

import com.main.functionlistsdemo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @auth lipf on 2014/12/31.
 */
public class TimeUtils {
    /**
     * 1小时毫秒数
     */
    public final static int ONE_HOUR_MILLIS = 1000 * 60 * 60;
    /**
     * 一天毫秒数
     */
    public final static int ONE_DAY_MILLIS = ONE_HOUR_MILLIS * 24;
    /**
     * 一天分钟数
     */
    public final static int ONE_DAY_MINUTE = 24 * 60;

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static long NOW_YEAR_TIME = 0;

    /**
     * 获取app统一时间展示格式，如 刚刚  2分钟前，1天前
     *
     * @param time
     * @param ctx
     * @return
     */
    public static String getTimeAgo(long time, Context ctx) {
        if (NOW_YEAR_TIME == 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            NOW_YEAR_TIME = calendar.getTimeInMillis();
        }
        Date curDate = currentDate();
        long now = curDate.getTime();
        if (time > now || time <= 0) {
            return "刚刚";
        }

        int dim = getTimeDistanceInMinutes(time);

        String timeAgo = null;

        if (dim == 0) {//刚刚
            return "刚刚";
        } else if (dim >= 1 && dim < 60) {//多少分钟前
            timeAgo = dim + "分钟";
        } else if (dim >= 60 && dim < 1440) { //1h~24h之间
            timeAgo = (int) Math.floor(dim / 60) + "小时";
        } else if (dim >= 1440 && dim < 43200) { //24h ~ 30d之内
//            timeAgo = (int)Math.ceil(dim/1440) + ctx.getResources().getString(R.string.date_util_unit_day);
            timeAgo = daysBetween(curDate, time) + "天";
        } else if (dim >= 43200 && time >= NOW_YEAR_TIME) { //24h ~ 30d之内
            Calendar cl = Calendar.getInstance();
            cl.setTimeInMillis(time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d日");
            return simpleDateFormat.format(cl.getTime());
        } else {//36h 以后按照时间显示
            Calendar cl = Calendar.getInstance();
            cl.setTimeInMillis(time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y年M月d日");
            return simpleDateFormat.format(cl.getTime());
        }

        return timeAgo + "前";
    }

    /**
     * 获取分钟差
     *
     * @param time
     * @return
     */
    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }

    /**
     * 计算日期之间的天数差
     *
     * @param now
     * @param day2
     * @return
     */
    private static int daysBetween(Date now, long day2) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(day2);
        return daysBetween(now, cl.getTime());
    }

    /**
     * 计算日期之间的天数差
     *
     * @param now
     * @param returnDate
     * @return
     */
    private static int daysBetween(Date now, Date returnDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(now);
        cReturnDate.setTime(returnDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
    }

    /**
     * 毫秒数转为天数
     *
     * @param intervalMs
     * @return
     */
    private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    /**
     * 设置成午夜时间
     *
     * @param calendar
     */
    private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 格式化日期，返回格式 ：yyyy/MM/dd HH:mm a
     * 如 2015/09/05 19:46 PM
     *
     * @param time 单位是秒
     * @return
     */
    public static String formatMinute12(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm a", Locale.ENGLISH);
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time * 1000);
        String dateString = formatter.format(cl.getTime());
        return dateString;
    }


    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String dateString = formatter.format(calendar.getTime());
        return dateString;
    }


    public static String formatTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        String dateString = formatter.format(cl.getTime());
        return dateString;
    }


    /**
     * 格式化日期，返回格式 ：HH:mm
     * 如  19:46
     *
     * @param time 单位是秒
     * @return
     */
    public static String formatMinute13(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time * 1000);
        String dateString = formatter.format(cl.getTime());
        return dateString;
    }

    /**
     * 格式化日期，返回格式 ：MM月dd日HH:mm
     * 如  19:46
     *
     * @param time 毫秒
     * @return
     */
    public static String formatTimeForManualWeight(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd aHH:mm ", Locale.CHINA);
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        String dateString = formatter.format(cl.getTime());
        return dateString;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatTimeWithChiness(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        String dateString = formatter.format(cl.getTime());
        return dateString;
    }

    /**
     * 格式化 秒 成 HH:mm:ss
     *
     * @param seconds
     * @return
     */
    public static String formatSecondsToHMS(int seconds) {
        int h = seconds / 3600;
        String h2 = ((h > 9) ? (h + "") : ("0" + h));
        int m = seconds / 60 % 60;
        String m2 = ((m > 9) ? (m + "") : ("0" + m));
        int s = seconds % 60;
        String s2 = ((s > 9) ? (s + "") : ("0" + s));
        return h2 + ":" + m2 + ":" + s2;
    }

    /**
     * 判断是否是今天
     *
     * @param timestampSeconds 秒
     * @return
     */
    public static boolean isRealToday(long timestampSeconds) {
        long timeInMillis = getTodayBeginMillis();
        return timestampSeconds >= timeInMillis && timestampSeconds < (timeInMillis + ONE_DAY_MILLIS);
    }

    /**
     * 是否是今天
     *
     * @param timestampSeconds 秒
     * @return
     */
    public static boolean isToday(long timestampSeconds) {
        long timeInMillis = getTodayBeginMillis();
        timestampSeconds *= 1000;
        return timestampSeconds >= timeInMillis && timestampSeconds < (timeInMillis + ONE_DAY_MILLIS);
    }

    /**
     * 是否是昨天
     *
     * @param timestampSeconds
     * @return
     */
    public static boolean isYesterday(long timestampSeconds) {
        long timeInMillis = getTodayBeginMillis();
        timestampSeconds *= 1000;
        return timestampSeconds < timeInMillis && timestampSeconds >= (timeInMillis - ONE_DAY_MILLIS);
    }

    /**
     * 获取今天刚刚开始的时间戳 ms
     *
     * @return 单位 ms
     */
    public static long getTodayBeginMillis() {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        long timeInMillis = cl.getTimeInMillis();
        return timeInMillis;
    }

    /**
     * 把秒格式化成分
     *
     * @param seconds 秒
     * @return 分
     */
    public static int getMinuteBySeconds(int seconds) {
        return seconds / 60;
    }



    public static String formatTimeStr(String timeStr) {

        long time = Long.parseLong(timeStr);

        Date date = new Date(time);

        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");

        return format.format(date);
    }


}
