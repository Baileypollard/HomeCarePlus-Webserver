package com.techprimers.security.securitydbexample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static String convertMsToTime(long ms)
    {
        Date date = new Date(ms);
        return new SimpleDateFormat("hh:mm a").format(date);
    }

}
