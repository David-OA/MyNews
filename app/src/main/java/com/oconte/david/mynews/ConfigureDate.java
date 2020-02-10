package com.oconte.david.mynews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfigureDate {

    private static final SimpleDateFormat DATE_FORMAT_API =
            new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
    private static final SimpleDateFormat DATE_FORMAT_DISPLAY =
            new SimpleDateFormat("dd/MM/yy", Locale.FRENCH);
    private static final SimpleDateFormat DATE_FORMAT_FROM_API =
            new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);

    private static boolean isDateValidFormat(String date){
        String[] datePart = date.split("/");
        int month = Integer.valueOf(datePart[1]);
        int day = Integer.valueOf(datePart[0]);
        int year = Integer.valueOf(datePart[2]);
        return (month > 0 && month <= 12 && day > 0 && day <= 31 && year >= 0 && year < 99);

    }

    public static String convertCalendarForDisplay(Calendar calendar){
        return DATE_FORMAT_DISPLAY.format(calendar.getTime());
    }

    public static String convertCalendarForAPI(Calendar calendar){
        return DATE_FORMAT_API.format(calendar.getTime());
    }

    public static Calendar convertUserDateToCalendar(String dateFromUser){
        Calendar calendar = Calendar.getInstance();
        try {
            if(isDateValidFormat(dateFromUser)) {
                Date date = DATE_FORMAT_DISPLAY.parse(dateFromUser);
                calendar.setTime(date);
                return calendar;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isDateAfterToday(Calendar date){
        Date todayDate = Calendar.getInstance().getTime();
        return todayDate.before(date.getTime());
    }

    public static boolean isEndDateBeforeBeginDate(Calendar beginDate, Calendar endDate){
        if(endDate != null && beginDate != null) {
            return (endDate.getTime().before(beginDate.getTime()));
        } else {
            return false;
        }
    }

    public static String convertDateFromAPIToDisplay(String dateString){
        String[] arrayDate = dateString.split("T");
        Date date = new Date();
        try {
            date = DATE_FORMAT_FROM_API.parse(arrayDate[0]);
        } catch (ParseException e) {
            return null;
        }

        return DATE_FORMAT_DISPLAY.format(date);

    }
}
