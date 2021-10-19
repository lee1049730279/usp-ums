package com.usp.ums;

import com.usp.framework.nats.jetstream.annotation.EnableJetStream;
import com.usp.framework.web.skywalking.EnableLogAspect;
import com.usp.framework.web.skywalking.LogTypeEnum;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Gileschu
 */
@SpringBootApplication
@ComponentScan(value = {"com.usp.ums", "com.usp.framework", "com.usp.basis"})
@EnableLogAspect(value = LogTypeEnum.REQ_CONTROLLER_NO_TRACE_NO_RESP)
@EnableJetStream
@EnableDubbo//开启dubbo注解驱动
@EnableAsync
@EnableDiscoveryClient
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}