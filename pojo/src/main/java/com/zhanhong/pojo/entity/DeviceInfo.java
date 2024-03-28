package com.zhanhong.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {
    private id id;
    private String ccreatedTime;
    private String additionalInfo;
    private String tenantId;
    private String customerId;
    private String name;
    private String type;
    private String label;
    private String deviceProfileId;
    private String deviceData;
    private String firmwareId;
    private String softwareId;
    private String customerTitle;
    private String customerIsPublic;
    private String deviceProfileName;

}
