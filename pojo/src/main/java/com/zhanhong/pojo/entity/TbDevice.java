package com.zhanhong.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbDevice {
    private DeviceInfo []data;
    private Integer totalPages;
    private Integer totalElements;
    private String hasNext;
}
