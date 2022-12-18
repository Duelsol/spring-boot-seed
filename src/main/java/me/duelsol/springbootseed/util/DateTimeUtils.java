package me.duelsol.springbootseed.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author 冯奕骅
 */
public class DateTimeUtils {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    private DateTimeUtils() {}

    public static long toEpochMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    public static LocalDateTime from(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZONE_ID);
    }

    public static ZonedDateTime withZoneSameInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime.withZoneSameInstant(ZONE_ID);
    }

}
