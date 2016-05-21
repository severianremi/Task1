package com.anja.task1.app.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Created by Anna on 18.04.2016.
 */
public class DateConverter {

    private static final DateTimeFormatter SELECTED_REQUEST_FORMATTER = new DateTimeFormatterBuilder()
            .appendDayOfMonth(2)
            .appendLiteral(' ')
            .appendMonthOfYearText()
            .appendLiteral(' ')
            .appendYear(4, 4)
            .toFormatter();

    private static final  DateTimeFormatter LIST_ITEM_FORMATTER = new DateTimeFormatterBuilder()
            .appendMonthOfYearShortText()
            .appendLiteral(". ")
            .appendDayOfMonth(2)
            .appendLiteral(", ")
            .appendYear(4, 4)
            .toFormatter();

    private DateConverter() {
    }

    public static String toSelectedOrderFormat(DateTime date){
        return SELECTED_REQUEST_FORMATTER.print(date);
    }

    public static String toListItemFormat (DateTime date){
        String localDate = LIST_ITEM_FORMATTER.print(date);
        return Character.toString(localDate.charAt(0)).toUpperCase() + localDate.substring(1);
    }
}
