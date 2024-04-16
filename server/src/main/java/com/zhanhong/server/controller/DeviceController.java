package com.zhanhong.server.controller;

import com.zhanhong.common.result.Result;
import com.zhanhong.pojo.vo.DeviceVo;
import com.zhanhong.server.config.TbServerProperties;
import com.zhanhong.server.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
@CrossOrigin(origins = "http://localhost:8080")
public class DeviceController {

    @Autowired
    private WorkTimeService workTimeService;
    @Autowired
    private TbServerProperties properties;

    @RequestMapping("/info")
    public Result getDeviceInfo(){
        int size = workTimeService.getDeviceIds
                ("Bearer \n"+workTimeService.getToken(properties.getUsername(), properties.getPassword()).getToken())
                .size();
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setTotalNum(size);
        return Result.success(deviceVo);
    }
}
