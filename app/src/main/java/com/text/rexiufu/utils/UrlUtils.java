package com.text.rexiufu.utils;

import android.util.Patterns;
import android.webkit.URLUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
    public static boolean isUrlWise(String str) {
        String[] arr = str.split("\\.");
        if (arr.length == 4){
            for (int i = 0; i < 4; i++) {
                if (!isNUM(arr[i]) || arr[i].length() == 0 || Integer.parseInt(arr[i]) > 255 || Integer.parseInt(arr[i]) < 0 || Integer.parseInt(arr[0]) > 223) {
                    return false;
                }
            }
        }else{
            if (!Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str)) return false;
        }
        return true;
    }

    public static boolean isUrl(String str) {
        if (Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个字符串是否是一个合法的ip地址：
     * 1 首先检查字符串的长度 最短应该是0.0.0.0 7位 最长 000.000.000.000 15位
     * 2 按.符号进行拆分，拆分结果应该是4段
     * 3 检查每个字符串是不是都是数字
     */
    public static boolean isIP(String str) {
        // 1、首先检查字符串的长度 最短应该是0.0.0.0 7位 最长 000.000.000.000 15位
        if (str.length() < 7 || str.length() > 15) return false;
        // 2、按.符号进行拆分，拆分结果应该是4段，"."、"|"、"^"等特殊字符必须用 \ 来进行转义
        // 而在java字符串中，\ 也是个已经被使用的特殊符号，也需要使用 \ 来转义
        String[] arr = str.split("\\.");
        if (arr.length != 4) return false;
        // 3、检查每个字符串是不是都是数字,ip地址每一段都是0-255的范围
        for (int i = 0; i < 4; i++) {
            if (!isNUM(arr[i]) || arr[i].length() == 0 || Integer.parseInt(arr[i]) > 255 || Integer.parseInt(arr[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个字符串是否是数字
     */
    public static boolean isNUM(String str) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
