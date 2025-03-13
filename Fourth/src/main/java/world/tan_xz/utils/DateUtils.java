package world.tan_xz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 谭轩钊
 * version 1.0
 * 规范日期格式
 */

public class DateUtils {
    public static boolean isWeekday(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(timestamp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            return day != Calendar.SATURDAY && day != Calendar.SUNDAY;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String parseMonth(String timestamp) {
        try {
            LocalDateTime dt = LocalDateTime.parse(timestamp,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return dt.getMonthValue() + "月";
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 解析时间戳，返回一周中的某一天（中文）
     * @param timestamp 时间戳
     * @return 周几（如：周一、周二等）
     */
    public static String parseDayOfWeek(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(timestamp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            switch (dayOfWeek) {
                case Calendar.MONDAY:
                    return "周一";
                case Calendar.TUESDAY:
                    return "周二";
                case Calendar.WEDNESDAY:
                    return "周三";
                case Calendar.THURSDAY:
                    return "周四";
                case Calendar.FRIDAY:
                    return "周五";
                case Calendar.SATURDAY:
                    return "周六";
                case Calendar.SUNDAY:
                    return "周日";
                default:
                    return "未知";
            }
        } catch (ParseException e) {
            return "未知";
        }
    }

    /**
     * 解析时间戳，返回具体的日期（格式：yyyy-MM-dd）
     * @param timestamp 时间戳
     * @return 日期字符串
     */
    public static String parseDay(String timestamp) {
        try {
            LocalDateTime dt = LocalDateTime.parse(timestamp,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return "未知";
        }
    }
}
