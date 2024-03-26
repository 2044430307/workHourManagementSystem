package com.zhanhong.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampUtil {
    public static LocalDate toDate(Integer timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(0)).toLocalDate();
    }

    public static long toTimestamp(LocalDate localDate){
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    public static long toTimestamp(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
