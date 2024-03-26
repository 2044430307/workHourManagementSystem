package com.zhanhong.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanhong.pojo.entity.TbData;
import com.zhanhong.pojo.entity.WorkTime;
import com.zhanhong.server.mapper.WorkTimeMapper;
import com.zhanhong.server.service.WorkTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    private WorkTimeMapper workTimeMapper;
    @Autowired
    private WorkTimeService workTimeService;

    @Test
    void contextLoads() {

    }

}
