package com.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by  on 16/6/23.
 */
public class DateUtil {
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    /**
     * 英文简写（默认）如：12-01
     */
    public static String MONTH_DAY = "MM-dd";
    /**
     * 中文简写（默认）如：12月01日
     */
    public static String MONTH_DAY_CN = "MM月dd日";

    /**
     * 英文简写（默认）如：12-01
     */
    public static String HOUR_MINUTE = "hh:mm";

    public static String HOUR_MINUTE2 = "MM.dd";

    /**
     * 格式化 1970.01.1
     */
    public static String YEAR_MONTH_DAY = "yyyy.MM.dd";

    public static final long DAY_MILLISECOND = 86400000;

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        return df.format(date).substring(0, 4);
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 格式化式时分秒
     *
     * @param second
     * @return
     */
    public static String formatSecond(Double second) {
        String html = "0秒";
        if (second != null) {
            Double s = second;
            String format;
            Object[] array;
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                format = "%1$,d时%2$,d分%3$,d秒";
                array = new Object[]{hours, minutes, seconds};
            } else if (minutes > 0) {
                format = "%1$,d分%2$,d秒";
                array = new Object[]{minutes, seconds};
            } else {
                format = "%1$,d秒";
                array = new Object[]{seconds};
            }
            html = String.format(format, array);
        }

        return html;

    }

    /**
     * 格式换成 12-01
     *
     * @param time
     * @return
     */
    public static String formatTimestampToDate(long time, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    /**
     * 格式换成 12-01
     *
     * @param time
     * @return
     */
    public static String formatMonthDay(long time) {
        DateFormat formatter = new SimpleDateFormat(MONTH_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    /**
     * 格式化成 12月01日
     *
     * @param time
     * @return
     */
    public static String formatMonthDayCN(long time) {
        DateFormat formatter = new SimpleDateFormat(MONTH_DAY_CN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    public static String formatYearsMonthDay(long time) {
        DateFormat formatter = new SimpleDateFormat(FORMAT_SHORT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    public static String formatMonthDay(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        long record_time = Long.parseLong(time);
        DateFormat formatter = new SimpleDateFormat(MONTH_DAY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(record_time);
        return formatter.format(calendar.getTime());
    }


    public static String formatHourMinute(long time) {
        DateFormat formatter = new SimpleDateFormat(HOUR_MINUTE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    public static String formatHourMinute(String time) {
        long record_time = Long.parseLong(time);
        DateFormat formatter = new SimpleDateFormat(HOUR_MINUTE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(record_time);
        return formatter.format(calendar.getTime());
    }


    public static String getTodyTime() {
        Date dt = new Date();
        DateFormat formatter = new SimpleDateFormat(MONTH_DAY);
        String nowTime = formatter.format(dt);
        return nowTime;
    }

    public static String getYearTodyTime() {
        Date dt = new Date();
        DateFormat formatter = new SimpleDateFormat(FORMAT_SHORT);
        String nowTime = formatter.format(dt);
        return nowTime;
    }

    /**
     * 获得今天在内的之前7天时间
     *
     * @return
     */
    public static String[] get7DayTime() {

        String[] time7Day = new String[7];
        for (int i = 6; i >= 0; i--) {
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.add(Calendar.DAY_OF_MONTH, i - 6);
            Date DDday = calendar.getTime();
            DateFormat formatter = new SimpleDateFormat(HOUR_MINUTE2);
            String Time = formatter.format(DDday);
            time7Day[i] = Time;
        }
        return time7Day;

    }

    /**
     * 获得指定天数在内的之前7天时间
     *
     * @return
     */
    public static String[] get7DayTime(String data) {


        SimpleDateFormat formatter = new SimpleDateFormat(MONTH_DAY);
        Date datenew = null;
        try {
            datenew = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] time7Day = new String[7];
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datenew);
            calendar.add(Calendar.DAY_OF_MONTH, -i);
            Date DDday = calendar.getTime();
            DateFormat formatte = new SimpleDateFormat(MONTH_DAY);
            String Time = formatte.format(DDday);
            time7Day[i] = Time;
        }
        return time7Day;
    }


    public static String formatDuring(int mss) {
        int hours = (mss % (60 * 24)) / (60);
        int minutes = (mss % (60));

        if (hours == 0) {
            return minutes + "分";
        }

        return hours + "小时" + minutes + "分";
    }

    public static String formatSleepSum(int mss) {
        int hours = (mss % (60 * 24)) / (60);
        int minutes = (mss % (60));

        if (hours == 0) {
            return minutes + "分";
        }

        return hours + ":" + minutes;
    }

    /**
     * 格式化run track time
     *
     * @param second
     * @return
     */
    public static String formatRunTrackTime(double second) {
        String html = "00:00";
        if (second != 0) {
            Double s = second;
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
            String minute;
            String secon;
            if (minutes < 10) minute = "0" + minutes;
            else minute = String.valueOf(minutes);
            if (seconds < 10) secon = "0" + seconds;
            else secon = String.valueOf(seconds);
            if (hours > 0) {
                html = hours + ":" + minute + ":" + secon;
                return html;
            } else if (minutes > 0) {
                html = "00:" + minute + ":" + secon;
                return html;
            } else {
                html = "00:00:" + secon;
                return html;
            }
        }

        return html;

    }

    /**
     * 获取年龄
     *
     * @param date
     * @return
     */
    public static int getAgeFromBirthday(String date) {
        try {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDay = format.parse(date);

            Calendar cal = Calendar.getInstance();

            if (cal.before(birthDay)) {
                return 0;
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(birthDay);

            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    //monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    } else {
                        //do nothing
                    }
                } else {
                    //monthNow>monthBirth
                    age--;
                }
            } else {
                //monthNow<monthBirth
                //donothing
            }

            return age;

        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 从 中文 “yyyy年 MM月 dd日” 到 “yyyy-MM-dd”
     *
     * @param text
     * @return
     */
    public static CharSequence fromConfigFirst(String text) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年 MM月 dd日");
        try {
            Date date = format.parse(text);
            format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return text;
        }
    }

    /**
     * 从 英文 “yyyy-MM-dd” 到  “yyyy年 MM月 dd日”
     *
     * @param text
     * @return
     */
    public static CharSequence fromConfigSecond(String text) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(text);
            format = new SimpleDateFormat("yyyy年 MM月 dd日");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return text;
        }
    }

    /**
     * 从 “yyyy年 MM月 dd日” 格式获得
     *
     * @param text
     * @return
     */
    public static Calendar getDateFromConfigFirst(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年 MM月 dd日");
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(format.parse(text));
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
            GregorianCalendar c = new GregorianCalendar();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.YEAR, -21);
            return c;
        }
    }

    /**
     * 文字时间转换为long类型时间
     *
     * @param text_time
     * @param tiem_format
     * @return
     */
    public static String getTime(String text_time, String tiem_format) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(tiem_format, Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(text_time);
            long i = date.getTime();
            re_time = String.valueOf(i);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return re_time;
    }

    /**
     * long类型时间转化为制定格式
     *
     * @param currenttimes
     * @param formatStr
     * @return
     */
    public static String longtamp2string(long currenttimes, String formatStr) {
        Date d1 = new Date(currenttimes);
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.getDefault());
        strDate = sdf.format(d1);
        return strDate;
    }


    /**
     * 获取指定milis距计算机时间零点的天数差,取当前时区
     *
     * @param timemilis
     * @return
     */
    public static int getDaysFrom19700101(long timemilis) {
        Calendar calendarZero = Calendar.getInstance();
//        calendarZero.set(1970, 0, 1, 0, 0, 0);
//        calendarZero.set(Calendar.MILLISECOND, 0);
        calendarZero.clear();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timemilis);

        long delta = calendar.getTimeInMillis() - calendarZero.getTimeInMillis();

        int days = (int) (delta / (1000 * 60 * 60 * 24));
        return days;
    }

    /**
     * 获取指定milis距当天凌晨0点0分0秒的时间差
     *
     * @param timestamp
     * @return
     */
    public static int getTodaySpansFrom0(long timestamp) {
        //确保数据是毫秒
        if (String.valueOf(timestamp).length() < "1450255984578".length()) {
            timestamp = timestamp * 1000;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);


        return calendar.get(Calendar.HOUR) * 60 * 60 * 1000 +
                calendar.get(Calendar.MINUTE) * 60 * 1000 +
                calendar.get(Calendar.SECOND) * 1000;
    }

    /**
     * 获取当天 0 点的 timemilis
     *
     * @return
     */
    public static long getToday0ClockTimeMilis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long milis = calendar.getTimeInMillis();

        return milis;
    }

    /**
     * 判断一个milis是否是今天内的时刻
     *
     * @param timemilis
     * @return
     */
    public static boolean isInToday(long timemilis) {
        Calendar calendarToday = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timemilis);
        return calendarToday.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && calendarToday.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                && calendarToday.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String formatTime(String time_format, long milis) {
        DateFormat formatter = new SimpleDateFormat(time_format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milis);
        return formatter.format(calendar.getTime());
    }

    /**
     * 计算两个时间相差几个月
     */
    public static int getCountMonths(Calendar date1, Calendar date2) throws ParseException {

        int year = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

        //开始日期若小月结束日期
        if (year < 0) {
            year = -year;
            return year * 12 + date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH);
        }

        return year * 12 + date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH);
    }

    /**
     * 获取当前时间占24小时的百分比
     *
     * @param timemilis
     * @return
     */
    public static float getTime24HourPercentage(long timemilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timemilis);
        return (float) calendar.getTime().getHours() / 24f;
    }


    /**
     * 判断两个时间戳是否是同一天
     *
     * @param ms1
     * @param ms2
     * @return
     */
    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(ms1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(ms2);

        return isSameDayOfCalendar(calendar1, calendar2);
    }

    /**
     * 判断是否是当天
     */
    public static boolean isSameDayOfCalendar(Calendar calendar1, Calendar calendar2) {
        return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE));
    }

    /**
     * 获取指定时间之后的所有date
     */
    public static List<Date> getDateToToday(long time) {
        long currentTime = System.currentTimeMillis();
        if (time > currentTime) {
            return null;
        }

        long formerTimes = currentTime - time;
        long havaDay = formerTimes / 86400000;

        List<Date> dates = new ArrayList<Date>();

        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= havaDay; i++) {
            calendar.setTimeInMillis(time + 86400000 * (i - 1));
            dates.add(calendar.getTime());
        }

        return dates;
    }

    /**
     * 获取一个时间对象
     *
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getDateFromHourAndMinute(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 清空calendar时间 小时，分钟，秒
     */
    public static Calendar cleanTime(Calendar mCalendar) {

        Calendar userCal = (Calendar) mCalendar.clone();
        userCal.set(Calendar.HOUR_OF_DAY, 0);
        userCal.set(Calendar.MINUTE, 0);
        userCal.set(Calendar.SECOND, 0);
        userCal.set(Calendar.MILLISECOND, 0);
        return userCal;
    }

    /**
     * 获取某一时间戳当日的0点
     *
     * @param l
     */
    public static Calendar getZeroOfDateFromAMilis(long l) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 检测一个日期是否和列表中的一个日期相同
     *
     * @param dates
     * @param date
     * @return
     */
    public static boolean inDuration(List<Date> dates, Date date) {
        for (Date d : dates) {
            if (isSameDay(d, date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 两个日期的年月日是否相同
     *
     * @param d
     * @param date
     * @return
     */
    private static boolean isSameDay(Date d, Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(d.getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date.getTime());
        return isSameDayOfCalendar(calendar1, calendar2);
    }

    /**
     * 获取某一时间所在天的23小时59分59秒
     *
     * @param time
     * @return
     */
    public static Calendar get235959ofDateFromAmilis(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.clear(Calendar.MILLISECOND);
        return calendar;
    }

    /**
     * 从列表中找出同一个日期的对象
     *
     * @param needSyncDates
     * @param date
     * @return
     */
    public static Date getSameDateFromList(List<Date> needSyncDates, Date date) {
        for (Date d : needSyncDates) {
            if (isSameDay(d, date)) {
                return d;
            }
        }

        return null;
    }
}
