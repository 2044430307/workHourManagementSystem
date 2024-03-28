package com.zhanhong.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanhong.common.result.Result;
import com.zhanhong.common.utils.DateFormatUtil;
import com.zhanhong.common.utils.HttpClientUtil;
import com.zhanhong.pojo.entity.*;
import com.zhanhong.server.config.TbServerProperties;
import com.zhanhong.server.mapper.WorkTimeMapper;
import com.zhanhong.server.service.WorkTimeService;
import lombok.Data;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class WorkTimeServiceImpl implements WorkTimeService {
    @Autowired
    private TbServerProperties tbServerProperties;
    @Autowired
    private WorkTimeMapper workTimeMapper;


    /**
     * 调用TB接口，拿到指定时间的单条设备数据
     * @param startTs
     * @param endTs
     * @param token
     * @param deviceId
     * @return
     */
    @Override
    public TbData getData(long startTs, long endTs, String token, String deviceId) {
        String url="http://"+tbServerProperties.getIp()+tbServerProperties.getStartUrl()+deviceId+tbServerProperties.getEndUrl();
        Map<String,String> params=new HashMap<>();
        params.put("startTs",String.valueOf(startTs));
        params.put("endTime",String.valueOf(endTs));
        String result = HttpClientUtil.doGet(url, params, token);
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject.toJavaObject(TbData.class);
    }

    @Override
    public Result getWorkTime(String deviceId, String date) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("device_id",deviceId);

        queryWrapper.eq("date", DateFormatUtil.stringToLocalDate(date));
        WorkTime workTime = workTimeMapper.selectOne(queryWrapper);
        if (workTime!=null){
            return Result.success(workTime);
        }
        return Result.error("数据不存在");
    }

    @Override
    public Result insertWorkTime(WorkTime workTime) {
        if (workTimeMapper.insert(workTime)==1){
            return Result.success("新增成功");
        }
        return Result.error("新增失败");
    }

    @Override
    public Result uppdateWorkTime(WorkTime workTime) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("device_id",workTime.getDeviceId());
        queryWrapper.eq("date",workTime.getDate());
        if (workTimeMapper.update(workTime, queryWrapper)==1){
            return Result.success();
        }
        return Result.error("修改失败");
    }

    /**
     * 从TB拿到所有设备id
     * @return
     */
    @Override
    public List<String> getDeviceIds(String token) {

        String url="http://"+tbServerProperties.getIp()+"/api/tenant/deviceInfos";
        Map<String,String> params=new HashMap<>();
        params.put("pageSize","99999999");
        params.put("page","0");
        String result = HttpClientUtil.doGet(url, params, token);
        JSONObject jsonObject = JSON.parseObject(result);
        TbDevice tbDevice = jsonObject.toJavaObject(TbDevice.class);
        DeviceInfo[] infos = tbDevice.getData();
        List<String> ids=new ArrayList<>();
        for (DeviceInfo info:infos) {
            ids.add(info.getId().getId());
        }
        return ids;
    }

    @Override
    public TbJWT getToken(String username, String password) {
        Map<String,String> prams=new HashMap<>();
        prams.put("username",username);
        prams.put("password",password);
        String result="";
        String url="http://"+tbServerProperties.getIp()+"/api/auth/login";
        try {
            result = HttpClientUtil.doPost4Json(url,prams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject.toJavaObject(TbJWT.class);
    }


}
