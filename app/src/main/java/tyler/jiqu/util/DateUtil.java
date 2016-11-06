package tyler.jiqu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/5  10:41.
 * @描述 ${TODO}.
 */
public class DateUtil {

    public static Date stringToDate(String dateString) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(dateString);
        return date;

    }

    public static String dateToWeek(Date date) {

        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static String dateToNum(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM" + "月" + "dd" + "日");
        return format.format(date);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return calendar.getTime();
    }

    /**
     * 获得指定日期的后一天
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return calendar.getTime();
    }
}
