package com.usp.ums.rpc;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ***********************************************************
 * Copyright © 2021 有数派 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * @Author zhanglei
 * @Description
 * @Date 2021/9/23
 */

@Slf4j
@Setter(onMethod_ = {@Autowired})
@DubboService
public class UmsApiImpl implements UmsApi {
}
    