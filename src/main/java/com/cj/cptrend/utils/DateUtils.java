package com.cj.cptrend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");//如2016-08-10 20:40

    public static int calcDateNums(String date1, String date2) throws ParseException {
        long from = simpleFormat.parse(date1).getTime();
        long to = simpleFormat.parse(date2).getTime();
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }


    public static String getCurrentDate() {
        return simpleFormat.format(new Date());
    }
}
