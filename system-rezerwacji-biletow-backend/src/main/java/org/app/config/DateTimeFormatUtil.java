package org.app.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {
    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    public static LocalDateTime parseDate(String date){
        return LocalDateTime.parse(date,FORMATTER);
    }

    public static String parseToString(LocalDateTime dateTime){
        return FORMATTER.format(dateTime);
    }
}
