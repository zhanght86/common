package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 获取工作日
 * 依赖配置库[公共常量]的[节假日,节假日周末上班]
 * User: ketqi
 * Date: 2015-10-14 10:23
 */
public final class WorkDayHelper {
    /**
     * 获取当前日期的下一个工作日
     *
     * @return 日期
     */
    public static Date getNextWorkDayDate() {
        return getWorkDayDate(null, 1);
    }

    /**
     * 根据指定日期获取指定频率后的工作日
     *
     * @param date    日期
     * @param counter 频率
     * @return 日期
     */
    public static Date getWorkDayDate(Date date, int counter) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date == null ? new Date() : date);

        List<String> holidayList = holidayList();
        List<String> weekendWorkList = weekendWorkList();

        for (int i = 1; i <= counter; i++) {
            while (true) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                if (!isWeekend(weekendWorkList, calendar) && !isHoliday(holidayList, calendar)) {
                    break;
                }
            }
        }

        return calendar.getTime();
    }

    /**
     * 判断一个日期是不是周末.
     *
     * @param date the date
     * @return true, if checks if is weekend
     */
    public static boolean isWeekend(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date == null ? new Date() : date);
        return isWeekend(calendar);
    }

    /**
     * 一个日期是不是节假日.
     *
     * @param date the date
     * @return true, if checks if is holiday
     */
    public static boolean isHoliday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date == null ? new Date() : date);
        return isHoliday(calendar);
    }

    /**
     * 判断一个日历是不是周末.
     *
     * @param calendar the calendar
     * @return true, if checks if is weekend
     */
    private static boolean isWeekend(Calendar calendar) {
        return isWeekend(null, calendar);
    }

    /**
     * 判断一个日历是不是周末.
     *
     * @param weekendWorkList weeken work list
     * @param calendar        the calendar
     * @return true, if checks if is weekend
     */
    private static boolean isWeekend(List<String> weekendWorkList, Calendar calendar) {
        String dateStr = DateUtils.formatMonthDay(calendar.getTime());

        List<String> wwl = weekendWorkList;
        if (wwl == null || wwl.isEmpty()) {
            wwl = weekendWorkList();
        }

        if (wwl != null && !wwl.isEmpty() && wwl.contains(dateStr)) {
            return false;
        }

        //判断是星期几
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 || dayOfWeek == CommonPlatformConstant.LENGTH_7;
    }

    /**
     * 一个日历是不是节假日.
     *
     * @param calendar the calendar
     * @return true, if checks if is holiday
     */
    private static boolean isHoliday(Calendar calendar) {
        return isHoliday(null, calendar);
    }

    /**
     * 一个日历是不是节假日
     *
     * @param holidayList 节假日列表
     * @param calendar    日历
     * @return true, if checks is holiday
     */
    private static boolean isHoliday(List<String> holidayList, Calendar calendar) {
        String dateStr = DateUtils.formatMonthDay(calendar.getTime());
        if (holidayList != null && !holidayList.isEmpty()) {
            return holidayList.contains(dateStr);
        }
        return holidayList().contains(dateStr);
    }

    /** 获取节假日列表 */
    private static List<String> holidayList() {
        String value = ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.HOLIDAY);
        return PlatformUtils.parse2StrList(value);
    }

    /** 节假日周末上班列表 */
    private static List<String> weekendWorkList() {
        String value = ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.WEEKEND_WORK);
        return PlatformUtils.parse2StrList(value);
    }
}
