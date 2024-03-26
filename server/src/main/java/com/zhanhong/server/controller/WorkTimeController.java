package com.zhanhong.server.controller;

import com.zhanhong.common.result.Result;
import com.zhanhong.server.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/workTime")
public class WorkTimeController {
    @Autowired
    private WorkTimeService workTimeService;

    @GetMapping("/getWorkTime")
    public Result getWorkTime(String deviceId, String date){
        return workTimeService.getWorkTime(deviceId,date);
    }
}
