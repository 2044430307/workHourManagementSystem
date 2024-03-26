package com.zhanhong.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("work_time")
public class WorkTime {
    //设备ID
    private String deviceId;
    //工作时间
    private LocalTime manHours;
    //日期
    private LocalDate date;
}
