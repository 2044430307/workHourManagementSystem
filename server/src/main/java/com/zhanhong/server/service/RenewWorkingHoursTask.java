package com.zhanhong.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanhong.common.utils.TimestampUtil;
import com.zhanhong.pojo.entity.TbData;
import com.zhanhong.pojo.entity.WorkTime;
import com.zhanhong.server.mapper.WorkTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class RenewWorkingHoursTask {
    @Autowired
    private WorkTimeMapper workTimeMapper;
    @Autowired
    private WorkTimeService workTimeService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void renewWorkTime(HttpServletRequest request){
        List<String> deviceIds = workTimeService.getDeviceIds(request);
        LocalDateTime now = LocalDateTime.now();
        long timestamp = TimestampUtil.toTimestamp(now);
        for (String deviceId:deviceIds) {
            TbData data = workTimeService.getData(timestamp, timestamp + 1l, request, deviceId);
            String[] data1 = data.getData();
            String[] thresholdValue = data.getThresholdValue();
            if (Float.valueOf(data1[0])>=Float.valueOf(thresholdValue[0])){
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("device_id",deviceId);
                WorkTime workTime = workTimeMapper.selectOne(queryWrapper);
                workTime.setManHours(workTime.getManHours().plusMinutes(10));
                workTimeMapper.updateById(workTime);
            }
        }

    }
}
