package com.zhanhong.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * tb上报数据
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class TbData {

    private String []electriEnergy;
    private String []thresholdValue;
    private String []data;
}
