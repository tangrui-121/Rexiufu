package com.text.rexiufu.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    public final static char[] upper = "零一二三四五六七八九十".toCharArray();

    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    public static int getWeek(){
        int a = 0;
        switch (TimeUtil.getCurTimeString(new SimpleDateFormat("E"))){
            case "周一":
                a = 1;
                break;
            case "周二":
                a = 2;
                break;
            case "周三":
                a = 3;
                break;
            case "周四":
                a = 4;
                break;
            case "周五":
                a = 5;
                break;
            case "周六":
                a = 6;
                break;
            case "周日":
                a = 7;
                break;
        }
        return a;
    }

    /**
     * 根据小写数字格式的日期转换成大写格式的日期
     *
     * @param date
     * @return
     */
    public static String getUpperDate(String date) {
        //支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
        if (date == null) return null;
        //非数字的都去掉
        date = date.replaceAll("\\D", "");
        if (date.length() != 8) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {//年
            sb.append(upper[Integer.parseInt(date.substring(i, i + 1))]);
        }
        sb.append("年");//拼接年
        int month = Integer.parseInt(date.substring(4, 6));
        if (month <= 10) {
            sb.append(upper[month]);
        } else {
            sb.append("十").append(upper[month % 10]);
        }
        sb.append("月");//拼接月

        int day = Integer.parseInt(date.substring(6));
        if (day <= 10) {
            sb.append(upper[day]);
        } else if (day < 20) {
            sb.append("十").append(upper[day % 10]);
        } else {
            sb.append(upper[day / 10]).append("十");
            int tmp = day % 10;
            if (tmp != 0) sb.append(upper[tmp]);
        }
        sb.append("日");//拼接日
        return sb.toString();
    }
}