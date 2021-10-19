package com.usp.ums.dto;

import lombok.Data;

/**
 * ***********************************************************
 * Copyright © 2021 有数派 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * @Author zhanglei
 * @Description
 * @Date 2021/10/18
 */

@Data
public class SwAlarmDTO {

    private Integer scopeId;
    private String scope;
    private String name;
    private String id0;
    private String id1;
    private String ruleName;
    private String alarmMessage;
    private Long startTime;
}
