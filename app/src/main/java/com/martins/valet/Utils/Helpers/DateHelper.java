package com.martins.valet.Utils.Helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by policante on 7/14/16.
 */
public class DateHelper {

    private static final String DatePatternDefault = "dd/MM/yyyy";
    private static final String DateTimePatternDefault = "dd/MM/yyyy HH:mm:ss";

    public static DateFormat getDateFormat(String pattern){
        DateFormat dtFormat = new SimpleDateFormat(pattern, new Locale("pt","BR"));
        dtFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return dtFormat;
    }

    public static Date convertDateFromString(String strDateTime) throws ParseException {
        return convertDateTimeFromString(strDateTime, DatePatternDefault);
    }

    public static Date convertDateTimeFromString(String strDateTime, String pattern) throws ParseException{
        return getDateFormat(pattern).parse(strDateTime);
    }

    public static String convertStringFromDateTime(Date strDateTime) throws ParseException{
        return convertStringFromDateTime(strDateTime, DateTimePatternDefault);
    }

    public static String convertStringFromDateTime(Date strDateTime, String pattern) throws ParseException{
        return getDateFormat(pattern).format(strDateTime);
    }

    public static String convertMinuteToHour(int min){
        int hours = min / 60;
        int minutes = min % 60;
        return String.format("%dh:%02dm", hours, minutes);
    }

}
