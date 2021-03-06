package com.usp.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableScheduling
public class TaskConfig {

    @Bean
    public TaskExecutor taskExecutor(){
    	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    	taskExecutor.setCorePoolSize(10);
    	taskExecutor.setMaxPoolSize(20);
    	taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

}
