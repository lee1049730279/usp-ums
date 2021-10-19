package com.usp.ums.service.impl;

import com.google.gson.Gson;
import com.usp.ums.config.EmailProperties;
import com.usp.ums.dto.SwAlarmDTO;
import com.usp.ums.service.MailService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

@Service
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class MailServiceImpl implements MailService {
    private EmailProperties emailProperties;
    private JavaMailSender sender;
    private Gson gson;
    @Async
    public void  sendMessage( List<SwAlarmDTO> alarmList){
        System.out.println(gson.toJson(emailProperties));
//        log.info(alarmList.toString());
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 发送者邮箱
//        message.setFrom("service@youshupai.com");
//        // 接收者邮箱
//        message.setTo("1049730279@qq.com");
//        // 主题
//        message.setSubject("告警邮箱");
//        String content = getContent(alarmList);
//        // 邮件内容
//        message.setText(content);
//        sender.send(message);
//        log.info("告警邮件已发送...");
    }

    private String getContent(List<SwAlarmDTO> alarmList) {
        StringBuilder sb = new StringBuilder();
        for (SwAlarmDTO dto : alarmList) {
            sb.append("scopeId: ").append(dto.getScopeId())
                    .append("\nscope: ").append(dto.getScope())
                    .append("\n目标 Scope 的实体名称: ").append(dto.getName())
                    .append("\nScope 实体的 ID: ").append(dto.getId0())
                    .append("\nid1: ").append(dto.getId1())
                    .append("\n告警规则名称: ").append(dto.getRuleName())
                    .append("\n告警消息内容: ").append(dto.getAlarmMessage())
                    .append("\n告警时间: ").append(dto.getStartTime())
                    .append("\n\n---------------\n\n");
        }

        return sb.toString();
    }
}

