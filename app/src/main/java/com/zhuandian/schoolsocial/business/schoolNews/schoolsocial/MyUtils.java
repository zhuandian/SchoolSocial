package com.zhuandian.schoolsocial.business.schoolNews.schoolsocial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc :
 * author：xiedong
 * date：2019/4/22
 */
public class MyUtils {
    public static String currentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }
}
