package com.zhanhong.server.service;

import com.zhanhong.common.result.Result;
import com.zhanhong.pojo.entity.TbData;
import com.zhanhong.pojo.entity.TbJWT;
import com.zhanhong.pojo.entity.WorkTime;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;


public interface WorkTimeService {
    TbData getData(long startTime, long endTime,String token,String deviceId);

    Result getWorkTime(String deviceId, String date);

    Result insertWorkTime(WorkTime workTime);

    Result uppdateWorkTime(WorkTime workTime);

    List<String> getDeviceIds(String token);

    TbJWT getToken(String username,String password);
}
