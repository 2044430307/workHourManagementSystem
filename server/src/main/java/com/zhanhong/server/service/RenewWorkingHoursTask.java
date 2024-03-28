package com.zhanhong.server.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanhong.common.utils.TimestampUtil;
import com.zhanhong.pojo.entity.TbData;
import com.zhanhong.pojo.entity.TbJWT;
import com.zhanhong.pojo.entity.WorkTime;
import com.zhanhong.server.config.TbServerProperties;
import com.zhanhong.server.mapper.WorkTimeMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Component
public class RenewWorkingHoursTask {
    @Autowired
    private WorkTimeMapper workTimeMapper;
    @Autowired
    private WorkTimeService workTimeService;
    @Autowired
    private TbServerProperties properties;

    private Logger logger=Logger.getLogger(RenewWorkingHoursTask.class);


    @Scheduled(cron = "0 0/10 * * * ? ")
    public void renewWorkTime(){
        logger.debug("开始更新工作时间------");
        TbJWT tbJWT = workTimeService.getToken(properties.getUsername(), properties.getPassword());
        String token="Bearer \n"+tbJWT.getToken();
        List<String> deviceIds = workTimeService.getDeviceIds(token);
        LocalDateTime now = LocalDateTime.of(2024,3,25,16,19,7);
        long timestamp = TimestampUtil.toTimestamp(now);
        for (String deviceId:deviceIds) {
            TbData data = workTimeService.getData(timestamp, timestamp + 1l, token, deviceId);
            if (data.getElectriEnergy()==null){
                continue;
            }

            Map<String,String> valueMap=JSONObject.parseObject(data.getData().substring(0, data.getData().length()-1).substring(1),Map.class);
            Map<String,String> thresholdValueMap=JSONObject.parseObject(data.getThresholdValue().substring(0, data.getThresholdValue().length()-1).substring(1),Map.class);
            if (Float.valueOf(valueMap.get("value"))>= Float.valueOf(thresholdValueMap.get("value"))){
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("device_id",deviceId);
                WorkTime workTime = workTimeMapper.selectOne(queryWrapper);
                if (workTime==null){
                    workTime=new WorkTime(deviceId,LocalTime.of(0,0,0),LocalDate.now());
                    workTimeMapper.insert(workTime);
                }
                workTime.setManHours(workTime.getManHours().plusMinutes(10));
                workTimeMapper.update(workTime,queryWrapper);
            }
        }
        logger.debug("结束更新工作时间------");
    }
}
