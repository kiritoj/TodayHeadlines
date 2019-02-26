package com.example.mifans.Java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//时间戳转换工具类
public class timeStamp {
    //时间-->时间戳
    public static String dateToStamp(String time) throws ParseException {
        String stap;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();//获取时间的时间戳
        stap = String.valueOf(ts);
        return stap;
    }
    //时间戳-->时间
    public static String stampToDate(String stap){
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(stap);
        Date date = new Date(lt);
        time = simpleDateFormat.format(date);
        return time;
    }


}
