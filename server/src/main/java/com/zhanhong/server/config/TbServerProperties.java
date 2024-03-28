package com.zhanhong.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "tbserver")
@Data
public class TbServerProperties {
    //ip地址
    private String ip;

    private String startUrl;

    private String endUrl;

    private String username;

    private String password;
}
