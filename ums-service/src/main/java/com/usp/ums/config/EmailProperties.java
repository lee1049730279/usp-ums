package com.usp.ums.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

/**
 * ***********************************************************
 * Copyright © 2021 有数派 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * @Author zhanglei
 * @Description
 * @Date 2021/10/19
 */


@Data
@Component
@ConfigurationProperties(prefix = "my-mail")
@Validated
public class EmailProperties {
    private Boolean devWarn;
    private Boolean stagingWarn;
    private Boolean releaseWarn;
    private Boolean prodWarn;
    private Map<String,Map<String,String[]>> receiver;


}
