package com.ruoyi.common.utils.file;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {


    /*** current yyyyMMdd
     */
    public static final String curPlainDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * current yyyy-MM-dd
     */
    public static final String curPlainDateNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * current HHmmss
     */
    public static final String cur24HrTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * current yyyy-MM-dd HH:mm
     */
    public static final String curHmTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * current yyyy-MM-dd HH:mm:ss
     */
    public static final String curFullTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * current yyyy-MM-dd HH:mm:ss
     */
    public static final String curOrderTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String getPlainDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String get24HrTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getFullTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String getPlainFullTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String getPlainFullTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * yyyyMMddHHmmss add time(s)
     *
     * @param plainStart
     * @param timeExpire
     * @return
     */
    public static String getPlainTimeExpire(String plainStart, int timeExpire) {
        return GeneralHelper.date2Str(GeneralHelper.addTime(GeneralHelper.str2Date(plainStart, "yyyyMMddHHmmss"), Calendar.SECOND, timeExpire), "yyyyMMddHHmmss");
    }

    /**
     * Convert wx date to yyyyMMdd
     *
     * @param gmt_payment
     * @return
     */
    public static String getWxPlainDate(String time_end) {
        try {
            return getPlainDate(GeneralHelper.str2Date(time_end, "yyyyMMddHHmmss"));
        } catch (Exception e) {
            return curPlainDate();
        }
    }

    /**
     * Convert wx date to HHmmss
     *
     * @param gmt_payment
     * @return
     */
    public static String getWx24HrTime(String time_end) {
        try {
            return DateUtil.get24HrTime(GeneralHelper.str2Date(time_end, "yyyyMMddHHmmss"));
        } catch (Exception e) {
            return cur24HrTime();
        }
    }

    /**
     * Convert alipay date to yyyyMMdd
     *
     * @param gmt_payment
     * @return
     */
    public static String getAliPlainDate(String gmt_payment) {
        return getGmtPlainDate(gmt_payment);
    }

    /**
     * Convert alipay date to HHmmss
     *
     * @param gmt_payment
     * @return
     */
    public static String getAli24HrTime(String gmt_payment) {
        return getGmt24HrTime(gmt_payment);
    }

    /**
     * yyyy-MM-dd HH:mm:ss to yyyyMMdd
     *
     * @param gmt_payment
     * @return
     */
    public static String getGmtPlainDate(String gmt_payment) {
        try {
            return DateUtil.getPlainDate(GeneralHelper.str2Date(gmt_payment, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return curPlainDate();
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss to HHmmss
     *
     * @param gmt_payment
     * @return
     */
    public static String getGmt24HrTime(String gmt_payment) {
        try {
            return DateUtil.get24HrTime(GeneralHelper.str2Date(gmt_payment, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return cur24HrTime();
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss to yyyyMMddHHmmss
     *
     * @param gmt_payment
     * @return
     */
    public static String getAliPlainFullTime(String gmt_payment) {
        try {
            return DateUtil.getPlainFullTime(GeneralHelper.str2Date(gmt_payment, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return curOrderTime();
        }
    }

    /**
     * 指定日期数值
     *
     * @param time
     * @return
     */
    public static long getPlainFullLong(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            return sdf.parse(time).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String fromDateToStr(String dateStr, String from, String to) {
        DateFormat fromFormat = new SimpleDateFormat(from);
        DateFormat toFormat = new SimpleDateFormat(to);
        try {
            return toFormat.format(fromFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }
    }

    /**
     * current HHmmss
     */
    public static final String getLastTime(int dtime) {
        Date now = new Date();
        Date now_10 = new Date(now.getTime() - dtime); // 10分钟前的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");// 可以方便地修改日期格式
        return dateFormat.format(now_10);
    }

    public static final String addMonth(int month) {
        Format f = new SimpleDateFormat("yyyyMM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        return f.format(c.getTime());
    }

    public static final Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static String getCurrentDateStr(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static Date strToDate(String dbdate, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(dbdate);
        return date;
    }

    public static String dateToStr(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        String datestr = df.format(date);
        return datestr;
    }

    /**
     * 获取当年的第一天
     *
     * @param year
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param year
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * yyyyMMdd - one day
     *
     * @return
     */
    public static String getYesToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
        return sdf.format(as);
    }

    /**
     * yyyyMMdd - one day
     *
     * @return
     */
    public static String getYesToday2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
        return sdf.format(as);
    }

    /**
     * yyyyMMdd - one day
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date as = new Date();
        return sdf.format(as);
    }


    /**
     * 获取指定时间对应的毫秒数
     *
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间日期转换
     *
     * @param strDate 字符串yyyyMMddHHmmss
     * @return 字符串YYYYMMDD hhmmss
     */
    public static String strToDateLong(String strDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(strDate);//先按照原格式转换为时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str = new SimpleDateFormat("yyyyMMdd hhmmss").format(date);//再将时间转换为对应格式字符串
        return str;
    }

    /**
     * 时间日期转换
     *
     * @param strDate 字符串yyyyMMddHHmmss
     * @return 字符串YYYYMMDD hhmmss
     */
    public static String strToDateLong2(String strDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(strDate);//先按照原格式转换为时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str = new SimpleDateFormat("YYYYMMDD").format(date);//再将时间转换为对应格式字符串
        return str;
    }


    /**
     * 获取一段时间内的时间
     *
     * @param startDateStr yyyyMMdd
     * @param endDateStr   yyyyMMdd
     * @return
     * @throws Exception
     */
    public static List<String> getDatesByRange(String startDateStr, String endDateStr) throws Exception {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.now().minusDays(1);

        if (StringUtils.isNotEmpty(endDateStr)) {
            endDate = LocalDate.parse(endDateStr, formatter);
        }
        if (!startDate.isBefore(endDate) && !startDate.isEqual(endDate)) {
            throw new Exception("startDate should be before endDate:" + startDateStr + " - " + endDateStr);
        }
        LocalDate date = startDate;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            dates.add(date.format(formatter));
            date = date.plusDays(1);
        }
        return dates;
    }


    /**
     * 两种格式的时间格式转换
     *
     * @param resoucrFormate yyyyMMddHHmmss
     * @param targetFormate  yyyyMMdd hhmmss
     * @param dateTime
     * @return
     * @throws Exception
     */
    public static String parseDateTime(String resoucrFormate, String targetFormate, String dateTime) throws Exception {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(resoucrFormate);//严格遵守大小写
        LocalDateTime currentTime = LocalDateTime.parse(dateTime, formatter1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(targetFormate);
        return currentTime.format(formatter2);
    }


    public static Date parseDateTime(String resoucrFormate, String dateTime) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(resoucrFormate);
        Date date = simpleDateFormat.parse(dateTime);
        return date;
    }


}
