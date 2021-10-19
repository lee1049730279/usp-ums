package com.usp.ums.controller;

import com.usp.framework.web.mvc.BaseController;
import com.usp.ums.dto.SwAlarmDTO;
import com.usp.ums.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * ***********************************************************
 * Copyright © 2021 有数派 Inc.All rights reserved.  *
 * ***********************************************************
 *
 * @Author zhanglei
 * @Description
 * @Date 2021/9/11
 */

@Api(tags = {"Mail"}, description = "邮件消息")
@RestController
@RequestMapping(value = "/mail")
@Validated
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class MailController extends BaseController {

    private MailService mailService;


    @PostMapping(value = "/receive")
    @ApiOperation(value = "邮件发送")
    public void receive(@RequestBody @Validated List<SwAlarmDTO> alarmList) {
        mailService.sendMessage(alarmList);
        log.info("邮件已发送...");
    }
}

