package com.zhanhong.common.utils;

import java.time.LocalDate;

public class DateFormatUtil {
    /**
     * 日期格式yyyy-mm-dd
     * @param date
     * @return
     */
    public static LocalDate stringToLocalDate(String date){
        Integer year=0;
        Integer month=0;
        Integer day=0;
        try{
             year=Integer.valueOf(date.substring(0,4));
             month=Integer.valueOf(date.substring(5,7));
             day=Integer.valueOf(date.substring(8));
        }catch (Exception e){
            System.out.println("请按照指定日期格式yyyy-mm-dd输入");
        }
        return LocalDate.of(year,month,day);
    }
}
