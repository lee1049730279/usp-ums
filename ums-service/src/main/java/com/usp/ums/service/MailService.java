package com.usp.ums.service;


import com.usp.ums.dto.SwAlarmDTO;

import java.util.List;

/**
 * ***********************************************************
 * Copyright © 2021 有数派 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * @Author zhanglei
 * @Description
 * @Date 2021/10/19
 */


public interface MailService {
    void  sendMessage( List<SwAlarmDTO> alarmList);
}
