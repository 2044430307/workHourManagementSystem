package com.zhanhong.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVo {
    private Integer onlineNum;
    private Integer totalNum;
    private Integer offlineNum;
    private Integer alarmNum;
}
