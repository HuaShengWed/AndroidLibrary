package com.huasheng.library.utils.time;

import android.annotation.SuppressLint;
import android.content.Context;

import com.huasheng.library.R;
import com.huasheng.library.utils.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * Time Util
 *
 * @date 2014-7-31
 */
public class TimeUtils {

    // Default date long
    public static final long DEFAULT_DATE = -5364691200000L;

    public static final String TIMEFORMAT = "yyyy-MM-dd HH:mm";
    public static final String TIMEFORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FULL = "yyyy-MM-dd E";
    public static final String TIMEFORMAT_YM = "yyyy-MM";
    public static final String TIMEFORMAT_YMD = "yyyy-MM-dd";
    public static final String TIMEFORMAT_CYMD = "yyyy年MM月dd日";
    public static final String TIMEFORMAT_CMD = "MM月dd日";
    public static final String TIMEFORMAT_ABBYMD = "yy-MM-dd";
    public static final String TIMEFORMAT_HMS = "HH:mm:ss";
    public static final String TIMEFORMAT_HM = "HH:mm";
    public static final String TIMEFORMAT_MDHM = "MM-dd HH:mm";
    public static final String TIMEFORMAT_MDHMS = "MM-dd HH:mm:ss";
    public static final String TIMEFORMAT_YMDHMS = "yyyy-MM-dd HH:mm";
    public static final String TIMEFORMAT_ABBYMDHMS = "yy-MM-dd HH:mm";

    public static final long TIME_ONE_SECOND = 1000;
    public static final long TIME_ONE_MINUTE = 60 * 1000;
    public static final long TIME_ON_HOUR = 60 * 60 * 1000;
    public static final long TIME_OF_A_DAY = 24 * 60 * 60 * 1000;
    public static final long TIME_OF_SEVEN_DAYS = TIME_OF_A_DAY * 7; // 7天
    public static final long TIME_OF_THIRTY_DAYS = TIME_OF_A_DAY * 30; // 30天

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间戳并转换成默认格式（精确到秒）的字符串
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        return getDateTimeFromTimestamp(getCurrentTimestamp());
    }

    /**
     * 获取当前时间戳并转换成默认格式（精确到天）的字符串
     *
     * @return yyyy-MM-dd HH:mm
     */
    public static String getCurrentDate() {
        return getDateFromTimestamp(getCurrentTimestamp());
    }

    /**
     * 将给定时间戳转换成指定格式字符串
     *
     * @param format
     * @param time
     * @return
     */
    public static String timestamp2String(String format, Date time) {
        if (format == null) {
            format = TIMEFORMAT;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (time == null) {
            return null;
        }

        return formatter.format(time);
    }

    /**
     * 将指定时间戳转换成默认格式（精确到天）的字符串
     *
     * @param timestamp Date
     * @return yyyy-MM-dd HH:mm
     */
    public static String getDateFromTimestamp(Date timestamp) {
        return timestamp2String(TIMEFORMAT, timestamp);
    }

    /**
     * 将指定时间戳转换成默认格式（精确到秒）的字符串
     *
     * @param timestamp
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeFromTimestamp(Date timestamp) {
        return timestamp2String(TIMEFORMAT_ALL, timestamp);
    }

    /**
     * 将字符串转换成日期信息
     *
     * @param dateString
     * @return yyyy-mm-dd
     */
    public static Date string2Date(String dateString) {
        try {
            return DateFormat.getDateInstance().parse(dateString);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 判断给定第一个字符串形式时间是否在第二个时间之后
     *
     * @param time1 字符串形式时间
     * @param time2 字符串形式时间
     * @return true:time1在time2之后
     */
    public static boolean after(String time1, String time2) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date1;
        Date date2;
        try {
            date1 = dateFormat.parse(time1);
            date2 = dateFormat.parse(time2);
        } catch (ParseException e) {
            return false;
        }
        return date1.after(date2);
    }

    /**
     * 判断给定第一个字符串形式时间是否在第二个时间之后(精确到秒)
     *
     * @param time1 字符串形式时间
     * @param time2 字符串形式时间
     * @return true:time1在time2之后
     */
    public static boolean afterTime(String time1, String time2) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        Date date1;
        Date date2;
        try {
            date1 = dateFormat.parse(time1);
            date2 = dateFormat.parse(time2);
        } catch (ParseException e) {
            return false;
        }
        return date1.after(date2);
    }

    /**
     * 判断给定第一个字符串形式时间是否在第二个时间之前
     *
     * @param time1 字符串形式时间
     * @param time2 字符串形式时间
     * @return true: time1在time2之前
     */
    public static boolean before(String time1, String time2) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date1;
        Date date2;
        try {
            date1 = dateFormat.parse(time1);
            date2 = dateFormat.parse(time2);
        } catch (ParseException e) {
            return false;
        }

        return date1.before(date2);
    }

    /**
     * 判断给定第一个字符串形式时间是否在第二个时间之前 (精确到秒)
     *
     * @param time1 字符串形式时间
     * @param time2 字符串形式时间
     * @return true: time1在time2之前
     */
    public static boolean beforeTime(String time1, String time2) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        Date date1;
        Date date2;
        try {
            date1 = dateFormat.parse(time1);
            date2 = dateFormat.parse(time2);
        } catch (ParseException e) {
            return false;
        }

        return date1.before(date2);
    }

    /**
     * 判断一个日期是否在另两个日期之间，日期为字符串形式
     *
     * @param midDate   日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return true: midDate在startDate在endDate之间
     */
    public static boolean between(String midDate, String startDate, String endDate) {
        boolean rt = false;
        try {
            if (!(before(midDate, startDate)) && !after(midDate, endDate)) {
                rt = true;
            }
        } catch (Exception e) {
        }

        return rt;
    }

    /**
     * 判断日期是否相等
     *
     * @param time1 字符串形式时间
     * @param time2 字符串形式时间
     * @return
     */
    public static boolean equals(String time1, String time2) {
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date date1 = dateFormat.parse(time1);
            Date date2 = dateFormat.parse(time2);
            return date1.equals(date2);
        } catch (Exception e) {
        }

        return false;
    }

    /**
     * 计算2个日期相差天数
     *
     * @param time1 日期
     * @param time2 日期
     * @return 返回 time1 - time2 的天数差
     */
    public static int compareDate(String time1, String time2) throws ParseException {
        int day = 0;
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date date1 = dateFormat.parse(time1);
            Date date2 = dateFormat.parse(time2);
            long quot;
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            day = Long.valueOf(quot).intValue();
        } catch (Exception e) {
        }

        return day;
    }

    /**
     * 计算2个日期相差天数
     *
     * @param time1 日期
     * @param date2 日期
     * @return 返回 time1 - date2 的天数差
     */
    public static int compareDate(String time1, Date date2) {
        int day = 0;
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date date1 = dateFormat.parse(time1);
            long quot;
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            day = Long.valueOf(quot).intValue();
        } catch (Exception e) {
        }

        return day;
    }

    /**
     * 计算2个日期相差天数
     *
     * @param date1 日期
     * @param date2 日期
     * @return 返回 date1 - date2 的天数差
     */
    public static int compareDate(Date date1, Date date2) {
        int day = 0;
        try {
            long quot;
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            day = Long.valueOf(quot).intValue();
        } catch (Exception e) {
        }

        return day;
    }

    /**
     * 计算2个时间差值(精确到分钟)
     *
     * @param time1  时间
     * @param time2  时间
     * @param format 时间格式（tim1与time2格式）
     * @return 返回 time1 - time2 的分钟差
     */
    public static int compareMinutes(String time1, String time2, String format) {
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(format);
        } catch (Exception e) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
        int minute = 0;
        try {
            Date date1 = simpleDateFormat.parse(time1);
            Date date2 = simpleDateFormat.parse(time2);
            long quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60;
            minute = Long.valueOf(quot).intValue();
        } catch (Exception e) {
        }

        return minute;
    }

    /**
     * 计算指定时间与当日0时的时间差(精确到分钟)
     *
     * @param time1 时间
     * @return 返回 time1 - time1当日00:00 的分钟差
     */
    @SuppressWarnings("unused")
    @SuppressLint({
            "SimpleDateFormat", "UseValueOf"
    })
    public static int compareMinutes(String time1) {
        SimpleDateFormat simpleDateFormat = null;
        int minute = 0;
        try {
            new SimpleDateFormat("yyyy-MM-dd hh:mm");
        } catch (Exception e) {
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date date2 = null;
            if (simpleDateFormat != null) {
                date1 = simpleDateFormat.parse(time1);
            }
            if (sdf != null) {
                date2 = sdf.parse(time1);
            }
            long quot = 0;
            if (date1 != null && date2 != null) {
                quot = date1.getTime() - date2.getTime();
            }
            quot = quot / 1000 / 60;
            minute = new Long(quot).intValue();
        } catch (Exception e) {
        }

        return minute;
    }

    /**
     * 时间差(精确到时)
     *
     * @param end   the end time
     * @param start the start time
     * @return
     */
    public static float compareHours(long end, long start) {
        if (end <= start) {
            return 0;
        }

        return (float) (end - start) / (float) (1000 * 3600);
    }

    /**
     * 时间差(精确到时)
     *
     * @param end   the end time
     * @param start the start time
     * @return
     * @Description Hours
     * @see this{@link #compareHours(long, long)}
     */
    @Deprecated
    public static long compareHours2(long end, long start) {
        if (end <= start) {
            return 0;
        }

        return ((end - start) / (3600 * 1000));
    }

    /**
     * 将给定字符串形式时间添加指定天数后返回
     *
     * @param date    日期
     * @param addDays 延迟天数
     * @return
     */
    public static String addDaysToDate(String date, int addDays) {
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date oldDate = dateFormat.parse(date);
            long t1 = oldDate.getTime();
            return getDateFromTimestamp(new Timestamp(t1 + addDays * TIME_OF_A_DAY));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 将给定字符串形式时间添加指定天数后返回 （精确到秒）
     *
     * @param date    日期
     * @param addDays 延迟天数
     * @return
     */
    public static String addDaysToDateTime(String date, int addDays) {
        try {
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            Date oldDate = dateFormat.parse(date);
            long t1 = oldDate.getTime();
            return getDateTimeFromTimestamp(new Timestamp(t1 + addDays * TIME_OF_A_DAY));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 将给定字符串形式时间添加指定月数后返回
     *
     * @param date      日期
     * @param addMonths 延迟月数
     * @return
     */
    public static String addMonthsToDate(String date, int addMonths) {
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date oldDate = dateFormat.parse(date);

            Calendar c = Calendar.getInstance();
            c.setTime(oldDate);
            c.add(Calendar.MONTH, addMonths);
            return fullfillDateTime(DateFormat.getDateInstance().format(c.getTime()));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 将给定字符串形式时间添加指定月数后返回(精确到秒)
     *
     * @param date      日期
     * @param addMonths 延迟月数
     * @return
     */
    public static String addMonthsToDateTime(String date, int addMonths) throws ParseException {
        try {
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            Date oldDate = dateFormat.parse(date);

            Calendar c = Calendar.getInstance();
            c.setTime(oldDate);
            c.add(Calendar.MONTH, addMonths);
            return fullDateTime(DateFormat.getDateTimeInstance().format(c.getTime()));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 将给定字符串形式时间在给定日历中添加指定工期（工作日）后返回
     *
     * @param date     日期
     * @param addDays  延迟天数
     * @param holidays 指定工期
     * @return
     */
    public static String addDaysToDateAccordingGivenCalendar(String date, int addDays, Collection<String> holidays) {
        String result = date;

        try {
            if (addDays > 0) {
                for (int i = 0; i < addDays; i++) {
                    result = getNextWorkday(result, holidays);
                }
            } else if (addDays < 0) {
                for (int i = 0; i > addDays; i--) {
                    result = getLastWorkday(result, holidays);
                }
            }
        } catch (Exception e) {
            result = date;
        }

        return result;
    }

    /**
     * 获取下一个工作日
     *
     * @param today    今天
     * @param holidays 工作日
     * @return
     */
    public static String getNextWorkday(String today, Collection<String> holidays) {
        try {
            String next = today;
            for (; ; ) {
                next = addDaysToDate(next, 1);
                if (!isHoliday(next, holidays)) {
                    return next;
                }
            }
        } catch (Exception e) {
        }

        return today;
    }

    /**
     * 获取上一个工作日
     *
     * @param today    今天
     * @param holidays 工作日
     * @return
     */
    public static String getLastWorkday(String today, Collection<String> holidays) {
        try {
            String last = today;
            for (; ; ) {
                last = addDaysToDate(last, -1);
                if (!isHoliday(last, holidays)) {
                    return last;
                }
            }
        } catch (Exception e) {
        }

        return today;
    }

    /**
     * 根据开始时间、工期和日历计算结算结束时间
     *
     * @param startDate 开始时间
     * @param workDates 工作日
     * @param holidays  工期
     * @return
     * @throws ParseException
     */
    public static String getEndDate(String startDate, int workDates, Collection<String> holidays) {
        try {
            return addDaysToDateAccordingGivenCalendar(startDate, workDates - 1, holidays);
        } catch (Exception e) {
        }

        return startDate;
    }

    /**
     * 根据日历计算两个日期间的工作日差距
     *
     * @param oldDate
     * @param newDate
     * @param holidays
     * @return
     */
    public static int getDelay(String oldDate, String newDate, Collection<String> holidays) {
        int workDates = getWorkdates(oldDate, newDate, holidays);
        return (workDates > 0) ? workDates - 1 : ((workDates < 0) ? workDates + 1 : 0);
    }

    /**
     * 判断给定日期是否节假日
     *
     * @param date
     * @param holidays
     * @return
     */
    public static boolean isHoliday(String date, Collection<String> holidays) {
        return holidays.contains(date);
    }

    /**
     * 根据开始时间、结束时间和日历计算工期
     *
     * @param startDate
     * @param endDate
     * @param holidays
     * @return
     */
    public static int getWorkdates(String startDate, String endDate, Collection<String> holidays) {
        if (equals(startDate, endDate)) {
            return 1;
        }

        try {
            boolean asc = before(startDate, endDate);
            String temp = startDate;
            int result = 0;
            for (; ; ) {
                if (asc) {
                    result++;
                } else {
                    result--;
                }
                temp = asc ? getNextWorkday(temp, holidays) : getLastWorkday(temp, holidays);

                if ((asc && after(temp, endDate)) || (!asc && before(temp, endDate))) {
                    return result;
                }
            }
        } catch (Exception e) {
        }

        return 1;
    }

    /**
     * 补全时间，如果2007-6-2补全为2007-06-02
     *
     * @param date
     * @return
     */
    public static String fullfillDateTime(String date) {
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date oldDate = dateFormat.parse(date);
            long t = oldDate.getTime();
            return getDateFromTimestamp(new Timestamp(t));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 补全时间，如果2007-6-2 00:00:00补全为2007-06-02 00:00:01精确到秒
     *
     * @param date
     * @return
     */
    public static String fullDateTime(String date) {
        try {
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            Date oldDate = dateFormat.parse(date);
            long t = oldDate.getTime();
            return getDateTimeFromTimestamp(new Timestamp(t));
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 补全时间，如果2007-6-2 00:00:00补全为2007-06-02 00:00:01精确到秒
     *
     * @param date
     * @return
     */
    public static String parseDateTime(String date) {
        try {
            Date oldDate = parseDate(date, TimeUtils.TIMEFORMAT_ALL);
            long t = oldDate.getTime();
            return getDateTimeFromTimestamp(new Timestamp(t));
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 获取给定日期是在当月的第几天
     *
     * @param date
     * @return 如2008-03-27返回的是27
     */
    public static int getDayOfMonth(String date) {
        Calendar c = Calendar.getInstance();
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date oldDate;
            oldDate = dateFormat.parse(date);
            c.setTime(oldDate);
        } catch (Exception e) {
        }

        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将所给日期字符串重新格式化
     * <p>
     * 如：<br>
     * <code>TimeUtils.reformatDate("20080204", "yyyyMMdd", "yyyy-MM-dd");</code>
     * <br>
     * <code>TimeUtils.reformatDate("2008-02-04", "yyyy-MM-dd", "yyyyMMdd");</code>
     * <br>
     *
     * @param date      日期
     * @param oldFormat 旧格式
     * @param newFormat 新格式
     * @return
     */
    public static String reformatDate(String date, String oldFormat, String newFormat) throws ParseException {
        try {
            DateFormat format = new SimpleDateFormat(oldFormat);
            Date d = format.parse(date);
            format = new SimpleDateFormat(newFormat);
            return format.format(d);
        } catch (Exception e) {
            return date;
        }
    }

    /**
     * 判断某时间是否是今天
     *
     * @param t
     * @return
     */
    public static boolean isSameDay(long t) {
        String srcString = longTime2StringTime(t, TIMEFORMAT);
        // 获得今天时哪年哪月哪日
        String todayString = getCurrentDate();
        if (!StringUtils.isNull(srcString) && !StringUtils.isNull(todayString)) {
            if (todayString.equals(srcString)) return true;
        }

        return false;
    }

    /**
     * 判断某时间是否是今年
     *
     * @param t
     * @return
     */
    public static boolean isSameYear(long t) {
        String srcString = longTime2StringTime(t, "yyyy");
        // 获得今天是哪年
        String thisYearString = timestamp2String("yyyy", getCurrentTimestamp());
        if (!StringUtils.isNull(srcString) && !StringUtils.isNull(thisYearString)) {
            if (thisYearString.equals(srcString)) return true;
        }

        return false;
    }

    /**
     * 是否是同一天
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameDay(long time1, long time2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        int y1 = cal1.get(Calendar.YEAR);
        int d1 = cal1.get(Calendar.DAY_OF_YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(time2);
        int y2 = cal2.get(Calendar.YEAR);
        int d2 = cal2.get(Calendar.DAY_OF_YEAR);

        if (y1 != y2) return false;

        return d1 == d2;
    }

    /**
     * 将long类型时间转换成字符串类型 形式 06:10 18:18:18
     *
     * @param time
     * @param pattern 格式
     * @return
     */
    public static String longTime2StringTime(long time, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(time));
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return yyyy-MM-dd HH:mm
     */
    public static String simpleDateFormat(Date date) {
        if (date == null) return null;
        SimpleDateFormat todayDateFormatter = new SimpleDateFormat(TIMEFORMAT);
        return todayDateFormatter.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param dateformat 格式
     * @return
     */
    public static String simpleDateFormat(Date date, String dateformat) {
        if (date == null) return null;

        if (dateformat == null) {
            dateformat = TIMEFORMAT;
        }
        SimpleDateFormat todayDateFormatter = new SimpleDateFormat(dateformat);
        return todayDateFormatter.format(date);
    }

    /**
     * 格式化日期
     *
     * @param milliseconds milliseconds为System.currentTimeMillis()
     * @param dateformat
     */
    public static String formatMilliseconds(long milliseconds, String dateformat) {
        if (dateformat == null) {
            dateformat = TIMEFORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateformat, Locale.SIMPLIFIED_CHINESE);
        return sdf.format(milliseconds);
    }

    /**
     * 格式化日期
     *
     * @param data
     * @param dateformat 格式
     * @return
     */
    public static Date parseDate(String data, String dateformat) {
        if (dateformat == null) {
            dateformat = TIMEFORMAT;
        }
        if (data == null || data.length() == 0) return null;

        try {
            DateFormat df = new SimpleDateFormat(dateformat);
            Date d_date = df.parse(data);
            return d_date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @param data
     * @param dateformat 格式
     * @param defaultV   异常情况的默认值
     * @return
     */
    public static long parseDate(String data, String dateformat, long defaultV) {
        if (dateformat == null) {
            dateformat = TIMEFORMAT_ALL;
        }

        if (data == null || data.length() == 0) return defaultV;

        try {
            DateFormat df = new SimpleDateFormat(dateformat);
            Date d_date = df.parse(data);
            if (d_date != null) {
                return d_date.getTime();
            }
        } catch (Exception e) {
            return defaultV;
        }

        return defaultV;
    }

    /**
     * 转换日期为毫秒形式
     *
     * @param data       日期
     * @param dateformat 日期格式
     * @return
     */
    public static long toMilliseconds(String data, String dateformat) {
        return toMilliseconds(data, dateformat, 0);
    }

    /**
     * 转换日期为毫秒形式
     *
     * @param data       日期
     * @param dateformat 日期格式
     * @param defaultV   异常情况默认值
     * @return
     */
    public static long toMilliseconds(String data, String dateformat, long defaultV) {
        if (dateformat == null) {
            dateformat = TIMEFORMAT_ALL;
        }

        if (data == null || data.length() == 0) return defaultV;

        try {
            DateFormat df = new SimpleDateFormat(dateformat);
            Date d_date = df.parse(data);
            if (d_date != null) {
                return d_date.getTime();
            }
        } catch (Exception e) {
            return defaultV;
        }

        return defaultV;
    }

    /**
     * string类型的时间转化成long类型的时间
     *
     * @param str
     * @param pattern
     * @return
     * @see this{@link #toMilliseconds(String, String)}
     */
    public static long convertLongTime4TimeStr(String str, String pattern) {
        if (str == null || str.trim().length() == 0) return 0;

        if (StringUtils.isNull(pattern)) {
            pattern = TIMEFORMAT_ALL;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(str);
            if (date == null) return 0;

            return date.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 查找Date
     *
     * @param dBegin
     * @param dEnd
     * @param pattern
     * @return
     */
    public static List<String> findDates(Calendar dBegin, Calendar dEnd, String pattern) {
        List<String> dateList = new ArrayList<String>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dBegin.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateList.add(sdf.format(cal.getTime()));
        } catch (Exception e) {
        }

        while (cal.getTimeInMillis() <= dEnd.getTimeInMillis()) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);

            try {
                dateList.add(sdf.format(cal.getTime()));
            } catch (Exception e) {
            }
        }

        return dateList;
    }

    /**
     * 查找Date
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Calendar> findDates(Calendar dBegin, Calendar dEnd) {
        List<Calendar> lDate = new ArrayList<Calendar>();
        lDate.add(dBegin);

        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin.getTime());

        // 测试此日期是否在指定日期之后
        while (dEnd.after(dBegin)) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin);

            if (calBegin.getTimeInMillis() > dEnd.getTimeInMillis()) {
                break;
            }
        }

        return lDate;
    }

    /**
     * 查找Date
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(dBegin);

        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }

        return lDate;
    }

    /**
     * 格式化日期
     *
     * @param data
     * @return yyyy-MM-dd HH:mm
     */
    public static Date parseDate(String data) {
        return parseDate(data, TIMEFORMAT);
    }

    /**
     * 转换为日期
     *
     * @param str     日期
     * @param pattern 格式
     * @return
     */
    public static Date convertDate4TimeStr(String str, String pattern) {
        if (str == null || str.trim().length() == 0) return null;

        if (StringUtils.isNull(pattern)) {
            pattern = TIMEFORMAT_ALL;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(str);
            if (date == null) return null;

            return date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * times
     *
     * @return 以半小时为间隔
     */
    public static String[] getTimes() {
        final String[] TM = new String[48];
        TM[0] = "00:00";
        TM[1] = "00:30";
        TM[2] = "01:00";
        TM[3] = "01:30";
        TM[4] = "02:00";
        TM[5] = "02:30";
        TM[6] = "03:00";
        TM[7] = "03:30";
        TM[8] = "04:00";
        TM[9] = "04:30";
        TM[10] = "05:00";
        TM[11] = "05:30";
        TM[12] = "06:00";
        TM[13] = "06:30";
        TM[14] = "07:00";
        TM[15] = "07:30";
        TM[16] = "08:00";
        TM[17] = "08:30";
        TM[18] = "09:00";
        TM[19] = "09:30";
        TM[20] = "10:00";
        TM[21] = "10:30";
        TM[22] = "11:00";
        TM[23] = "11:30";
        TM[24] = "12:00";
        TM[25] = "12:30";
        TM[26] = "13:00";
        TM[27] = "13:30";
        TM[28] = "14:00";
        TM[29] = "14:30";
        TM[30] = "15:00";
        TM[31] = "15:30";
        TM[32] = "16:00";
        TM[33] = "16:30";
        TM[34] = "17:00";
        TM[35] = "17:30";
        TM[36] = "18:00";
        TM[37] = "18:30";
        TM[38] = "19:00";
        TM[39] = "19:30";
        TM[40] = "20:00";
        TM[41] = "20:30";
        TM[42] = "21:00";
        TM[43] = "21:30";
        TM[44] = "22:00";
        TM[45] = "22:30";
        TM[46] = "23:00";
        TM[47] = "23:30";
        return TM;
    }

    /**
     * 检测字符串是否为时间格式
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean checkDateTime(String str, String pattern) {
        if (str == null || str.trim().length() == 0) return false;

        if (StringUtils.isNull(pattern)) {
            pattern = TIMEFORMAT_ALL;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(str);
            if (date == null) return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * get Week Start
     *
     * @return
     */
    public static String getWeekStart() {
        return getWeekStart(getWeekRange());
    }

    /**
     * Week Start
     *
     * @param date
     * @return
     */
    public static String getWeekStart(Date date) {
        return getWeekStart(getWeekRange(date));
    }

    /**
     * Week Start
     *
     * @param weekRange
     * @return
     */
    public static String getWeekStart(String weekRange) {
        return weekRange.split("\\s~\\s")[0];
    }

    /**
     * Week End
     *
     * @return
     */
    public static String getWeekEnd() {
        return getWeekEnd(getWeekRange());
    }

    /**
     * Week End
     *
     * @param date
     * @return
     */
    public static String getWeekEnd(Date date) {
        return getWeekEnd(getWeekRange(date));
    }

    /**
     * Week End
     *
     * @param weekRange
     * @return
     */
    public static String getWeekEnd(String weekRange) {
        return weekRange.split("\\s~\\s")[1];
    }

    /**
     * 获取给定日期的周信息
     *
     * @param date
     * @return 如2007-12-04返回的是2007-12-02 ~ 2007-12-08
     */
    public static String getWeekRange(String date) {
        return getWeekRange(string2Date(date));
    }

    /**
     * 获取给当前日期的周信息
     *
     * @return 如2007-12-04返回的是2007-12-02 ~ 2007-12-08
     */
    public static String getWeekRange() {
        return getWeekRange(new Date());
    }

    /**
     * 获取给定日期的周信息
     *
     * @param date
     * @return 如2007-12-04返回的是2007-12-02 ~ 2007-12-08
     */
    public static String getWeekRange(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        /*
         * 日期从周一到周日 Date monday = (Date) date.clone();
		 * monday.setTime(monday.getTime() - TIME_OF_A_DAY * (dayOfWeek-1-1));
		 * Date sunday = (Date) date.clone(); sunday.setTime(sunday.getTime() +
		 * TIME_OF_A_DAY * (7-dayOfWeek+1)); return getDateFromTimestamp(monday)
		 * + " ~ " + getDateFromTimestamp(sunday);
		 */
        Date sunday = (Date) date.clone();
        sunday.setTime(sunday.getTime() - TIME_OF_A_DAY * (dayOfWeek - 1));
        Date saturday = (Date) date.clone();
        saturday.setTime(saturday.getTime() + TIME_OF_A_DAY * (7 - dayOfWeek));

        return getDateFromTimestamp(sunday) + " ~ " + getDateFromTimestamp(saturday);

    }

    /**
     * 获取给定日期是在当年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(String date) {
        return getWeekOfYear(string2Date(date));
    }

    /**
     * 获取给定日期是在当年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前日期是在当年的第几周
     *
     * @return
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * 获取给定日期周信息
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        if (null != date)
            cal.setTime(date);
        else
            cal.setTimeInMillis(System.currentTimeMillis());

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取给定日期周信息
     *
     * @param timeMillis
     * @return
     */
    public static int getDayOfWeek(long timeMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeMillis);

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取今天是周几
     *
     * @param context
     * @return from string resouce
     */
    public static String getWeekDayString(Context context) {
        String weekString = "";

        // { "周末", "周一", "周二", "周三", "周四", "周五", "周六"};
        final String dayNames[] = context.getResources().getStringArray(R.array.day_of_week);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];

        return weekString;
    }

    /**
     * 获取今天是2012-01-01 周几
     *
     * @param context
     * @return from string resouce
     */
    public static String getDateWeekDayString(Context context) {
        String weekString = "";

        // { "周末", "周一", "周二", "周三", "周四", "周五", "周六"};
        final String dayNames[] = context.getResources().getStringArray(R.array.day_of_week);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];
        return TimeUtils.timestamp2String(TimeUtils.TIMEFORMAT, date) + " " + weekString;
    }

    /**
     * 获取date是周几
     *
     * @param context
     * @param date
     * @return from string resouce
     */
    public static String getDateWeekDayString(Context context, String date) {
        String weekString = "";

        // { "周末", "周一", "周二", "周三", "周四", "周五", "周六"};
        final String dayNames[] = context.getResources().getStringArray(R.array.day_of_week);

        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date oldDate = dateFormat.parse(date);
            calendar.setTime(oldDate);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            weekString = dayNames[dayOfWeek - 1];
            return TimeUtils.timestamp2String(TimeUtils.TIMEFORMAT, oldDate) + " " + weekString;
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 判断某天是星期几
     *
     * @param context
     * @param year
     * @param month
     * @param day
     * @return from string resouce
     */
    public static String getWeekDayString(Context context, int year, int month, int day) {
        String weekString = "";

        // { "周末", "周一", "周二", "周三", "周四", "周五", "周六"};
        final String dayNames[] = context.getResources().getStringArray(R.array.day_of_week);

        Calendar calendar = Calendar.getInstance();
        // 初始年份为1900年
        Date date = new Date(year - 1900, month - 1, day);
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];
        return weekString;
    }

    /**
     * 获取给定日期周信息
     *
     * @param timeMillis
     * @return
     */
    public static String getDayOfWeekFromStringResouce(long timeMillis) {
        return getDayOfWeekFromStringResouce(getDayOfWeek(timeMillis));
    }

    /**
     * 获取给定日期周信息
     * <p>
     * <pre>
     * 	<string name="monday">周一</string>
     *  <string name="tuesday">周二</string>
     *  <string name="wednesday">周三</string>
     *  <string name="thursday">周四</string>
     *  <string name="friday">周五</string>
     *  <string name="saturday">周六</string>
     *  <string name="sunday">周日</string>
     * </pre>
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekFromStringResouce(Date date) {
        return getDayOfWeekFromStringResouce(getDayOfWeek(date));
    }

    /**
     * 获取周信息
     * <p>
     * <pre>
     * 	<string name="monday">周一</string>
     *  <string name="tuesday">周二</string>
     *  <string name="wednesday">周三</string>
     *  <string name="thursday">周四</string>
     *  <string name="friday">周五</string>
     *  <string name="saturday">周六</string>
     *  <string name="sunday">周日</string>
     * </pre>
     *
     * @param week int
     * @return
     */
    public static String getDayOfWeekFromStringResouce(int week) {
        String chineseWeek = "";
        /*switch (week) {
            case Calendar.SUNDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.sunday);
				break;
			case Calendar.MONDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.monday);
				break;
			case Calendar.TUESDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.tuesday);
				break;
			case Calendar.WEDNESDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.wednesday);
				break;
			case Calendar.THURSDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.thursday);
				break;
			case Calendar.FRIDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.friday);
				break;
			case Calendar.SATURDAY:
				chineseWeek = PcBaseApplicationImpl.mOurApplication.getString(R.string.saturday);
				break;
		}*/

        return chineseWeek;
    }

    /**
     * 根据年月判断某月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        int days = 0;

        Calendar c = new GregorianCalendar(year, month, 0);

        days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 在当前时间上面加上十五分钟
     *
     * @param date
     * @return 格式化的时间格式
     */
    public static String getDateStringAdd15Minutes(String date) {
        if (StringUtils.isNull(date)) {
            return null;
        }
        String s = "";
        long time = TimeUtils.convertLongTime4TimeStr(date, TIMEFORMAT_ALL);
        if (time <= 0) {
            return s;
        }
        time += 15 * 60 * 1000;// 加上十五分钟的时间
        s = longTime2StringTime(time, TIMEFORMAT_ALL);
        return s;
    }


    /**
     * 大于十天
     *
     * @param time
     * @return
     */
    public static Long getDateStringAdd10day(long time) {

        time -= TIME_OF_A_DAY * 10;

        return time;
    }

    /**
     * 日期
     * <pre>
     *  小于1 天                HH:MM
     *  大于 1 天  小于 10天     天数
     *  大于十天   日期格式      TIMEFORMAT_ALL
     * @param mContext
     * @param time
     * @return
     */
    public static String toCustomDataInfo(Context mContext, long time) {

        if (null == mContext) {
            return formatMilliseconds(time, TIMEFORMAT_YMD);
        }

        if (time < getDateStringAdd10day(System.currentTimeMillis())) {
            return mContext.getString(R.string.before_time_data_arg, formatMilliseconds(time, TIMEFORMAT_YMD));
        } else {
            long dTime = System.currentTimeMillis() - time;
            // 24 小时之内
            if (dTime < 24 * TIME_ON_HOUR) {
                return mContext.getString(R.string.before_time_hour_arg, String.valueOf(dTime / TIME_ON_HOUR));
            } else {
                return mContext.getString(R.string.before_time_day_arg, String.valueOf(dTime / TIME_OF_A_DAY));
            }
        }
    }


    /**
     * 日期
     * <pre>
     * 	今天					HH:MM
     * 	昨天					昨天
     * 	同一个星期			星期x
     * 	不是同一个星期		MM-dd
     * 	不是同一年			yyyy-MM-dd
     *
     * @param context
     * @param time
     * @return
     */

    public static String toCustomDate(Context context, long time) {
        if (null == context) {
            return formatMilliseconds(time, TIMEFORMAT_MDHM);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int thisWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

        cal.setTimeInMillis(time);

        // 2014-9-06
        if (cal.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) {
            return formatMilliseconds(time, TIMEFORMAT_YMD);
        }
        // 今年
        else {
            String yyMMdd = formatMilliseconds(time, TIMEFORMAT_ABBYMD);
            String yesterday = formatMilliseconds(System.currentTimeMillis() - 1000 * 3600 * 24, TIMEFORMAT_ABBYMD);

            // 今天 显示16:27
            if (StringUtils.equals(formatMilliseconds(System.currentTimeMillis(), TIMEFORMAT_ABBYMD), yyMMdd)) {
                return formatMilliseconds(time, TIMEFORMAT_HM);
            }

            // 昨天 显示昨天
            else if (yyMMdd.equals(yesterday)) {
                return context.getString(R.string.yesterday);
            }

            // 显示同一个星期信息，且为同一个星期
            if (thisWeekOfYear == cal.get(Calendar.WEEK_OF_YEAR)) {
                switch (cal.get(Calendar.DAY_OF_WEEK)) {
                    case Calendar.SUNDAY:
                        return context.getString(R.string.sunday);

                    case Calendar.MONDAY:
                        return context.getString(R.string.monday);

                    case Calendar.TUESDAY:
                        return context.getString(R.string.tuesday);

                    case Calendar.WEDNESDAY:
                        return context.getString(R.string.wednesday);

                    case Calendar.THURSDAY:
                        return context.getString(R.string.thursday);

                    case Calendar.FRIDAY:
                        return context.getString(R.string.friday);

                    case Calendar.SATURDAY:
                        return context.getString(R.string.saturday);

                    default:
                        return formatMilliseconds(time, "MM-dd");
                }
            } else {
                return formatMilliseconds(time, "MM-dd");
            }

        }
    }

    /**
     * 自定义显示时间格式
     *
     * @param context
     * @param time            时间
     * @param showJust        显示刚刚
     * @param showBeforeTime  显示几分钟以前
     * @param showThisWeek    显示本周
     * @param accurateSeconds 精确到秒
     * @return
     */
    public static String toCustomTime(Context context, long time, boolean showJust, boolean showBeforeTime, boolean showThisWeek, boolean accurateSeconds) {
        if (null == context) {
            return formatMilliseconds(time, TIMEFORMAT_MDHM);
        }

        String strTime = "";
        long currTime = System.currentTimeMillis();

        if (time <= currTime) {
            // 1分钟之内显示刚刚
            if (showJust) {
                if (System.currentTimeMillis() - time <= TIME_ONE_MINUTE) {
                    return context.getString(R.string.time_just);
                }
            }

            // 12 小时之类显示，xxx小时 以前 or xxx分钟 以前
            if (showBeforeTime) {
                long dTime = System.currentTimeMillis() - time;

                // 1小时之内
                if (dTime < TIME_ON_HOUR) {
                    return context.getString(R.string.before_time_min_arg, String.valueOf(dTime / TIME_ONE_MINUTE));
                }

                // 8 小时之内
                else if (dTime < 8 * TIME_ON_HOUR) {
                    return context.getString(R.string.before_time_hour_arg, String.valueOf(dTime / TIME_ON_HOUR));
                }
            }
            // return formatMilliseconds(time, TIMEFORMAT_HM);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currTime);
        int today = cal.get(Calendar.DAY_OF_YEAR);
        int thisWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        int thisYear = cal.get(Calendar.YEAR);

        cal.setTimeInMillis(time);

        // 2014-9-06 16:40
        if (cal.get(Calendar.YEAR) != thisYear) {
            if (accurateSeconds) {
                strTime = formatMilliseconds(time, TIMEFORMAT_ALL);
            } else {
                strTime = formatMilliseconds(time, TIMEFORMAT_YMDHMS);
            }
        }
        // 今年
        else {
            // 1分钟之内显示刚刚
            if (showJust) {
                if (System.currentTimeMillis() - time <= TIME_ONE_MINUTE) {
                    return context.getString(R.string.time_just);
                }
            }

            // 12 小时之类显示，xxx小时 以前 or xxx分钟 以前
            if (showBeforeTime) {
                long dTime = System.currentTimeMillis() - time;

                // 1小时之内
                if (dTime < TIME_ON_HOUR) {
                    return context.getString(R.string.before_time_min_arg, dTime / TIME_ONE_MINUTE);
                }
                // 8 小时之内
                else if (dTime < 8 * TIME_ON_HOUR) {
                    return context.getString(R.string.before_time_hour_arg, String.valueOf(dTime / TIME_ON_HOUR));
                }
            }

            String yesterday = formatMilliseconds(System.currentTimeMillis() - 1000 * 3600 * 24, TIMEFORMAT_ABBYMD);
            String yyMMdd = formatMilliseconds(time, TIMEFORMAT_ABBYMD);

            // 今天16:27
            if (StringUtils.equals(formatMilliseconds(System.currentTimeMillis(), TIMEFORMAT_ABBYMD), yyMMdd)) {
                if (accurateSeconds) {
                    strTime = context.getString(R.string.today_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                } else {
                    strTime = context.getString(R.string.today_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                }
            }

            // 昨天16:27
            else if (yyMMdd.equals(yesterday)) {
                if (accurateSeconds) {
                    strTime = context.getString(R.string.yesterday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                } else {
                    strTime = context.getString(R.string.yesterday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                }
            } else {
                // 显示同一个星期信息，且为同一个星期
                if (showThisWeek && thisWeekOfYear == cal.get(Calendar.WEEK_OF_YEAR)) {
                    switch (cal.get(Calendar.DAY_OF_WEEK)) {
                        case Calendar.SUNDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.sunday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.sunday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.MONDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.monday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.monday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.TUESDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.tuesday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.tuesday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.WEDNESDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.wednesday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.wednesday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.THURSDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.thursday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.thursday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.FRIDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.friday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.friday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;

                        case Calendar.SATURDAY:
                            if (accurateSeconds) {
                                strTime = context.getString(R.string.saturday_arg, formatMilliseconds(time, TIMEFORMAT_HMS));
                            } else {
                                strTime = context.getString(R.string.saturday_arg, formatMilliseconds(time, TIMEFORMAT_HM));
                            }
                            break;
                    }
                } else {
                    if (accurateSeconds) {
                        strTime = formatMilliseconds(time, TIMEFORMAT_MDHMS);
                    } else {
                        strTime = formatMilliseconds(time, TIMEFORMAT_MDHM);
                    }
                }
            }
        }

        return strTime;
    }

    /**
     * 自定义显示时间格式
     *
     * @param context
     * @param time            时间
     * @param showThisWeek    显示与time相同周的周信息
     * @param accurateSeconds 精确到秒
     * @return
     */
    public static String toCustomTime(Context context, long time, boolean showThisWeek, boolean accurateSeconds) {
        return toCustomTime(context, time, false, false, showThisWeek, accurateSeconds);
    }

    /**
     * 自定义显示时间格式
     *
     * @param context
     * @param time
     * @param accurateSeconds 精确到秒
     * @return
     */
    public static String toCustomTime(Context context, long time, boolean accurateSeconds) {
        return toCustomTime(context, time, false, accurateSeconds);
    }

    /**
     * 时间格式
     *
     * @param context
     * @param time
     * @param accurateSeconds 精确到秒
     * @return
     */
    public static String toCustomTime(Context context, String time, boolean accurateSeconds) {
        long lonTime = parseDate(time, TIMEFORMAT_ALL, System.currentTimeMillis());
        return toCustomTime(context, lonTime, accurateSeconds);
    }

    /**
     * 自定义显示时间格式
     *
     * @param context
     * @param time
     * @return
     */
    public static String toCustomTime(Context context, String time) {
        long lonTime = parseDate(time, TIMEFORMAT_ALL, System.currentTimeMillis());
        return toCustomTime(context, lonTime, false);
    }

    /**
     * 自定义显示时间格式
     *
     * @param context
     * @param time
     * @return
     */
    public static String toCustomTime(Context context, long time) {
        return toCustomTime(context, time, false);
    }

    /**
     * 获取上次会议列表的下拉刷新时间
     *
     * @return
     */
    public static String getMeetListLastRefreshTime() {
        return longTime2StringTime(System.currentTimeMillis(), TIMEFORMAT_MDHM);
    }


}
