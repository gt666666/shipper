package com.zxhz.utils;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/26
 */
public class Tools {

    public static Boolean isBlank(String str) {
        if (str != null) {
            str = str.replaceAll("\r\n|\n\r|\n|\r|\f|\t", "");
        }
        if (str == null) {
            return true;
        } else if ("".equals(str)) {
            return true;
        } else if ("null".equals(str)) {
            return true;
        } else if ("NULL".equals(str)) {
            return true;
        } else if ("(null)".equals(str)) {
            return true;
        } else if ("(NULL)".equals(str)) {
            return true;
        } else if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static Boolean isBlank(Object obj) {
        if (obj != null) {
            return isBlank(String.valueOf(obj));
        }
        return true;
    }
}
