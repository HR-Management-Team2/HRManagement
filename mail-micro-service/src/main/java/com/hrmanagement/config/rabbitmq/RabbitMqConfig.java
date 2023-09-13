package com.hrmanagement.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //Auth register consumer
    private String mailRegisterQueue = "mail-register-queue";
    @Bean
    Queue mailRegisterQueue(){
        return new Queue(mailRegisterQueue);
    }

    //Auth activateManager consumer
    private String mailActivateQueue = "mail-activate-queue";

    Queue mailActivateQueue(){
        return new Queue(mailActivateQueue);
    }


    //Auth forgotPass consumer
    private String forgotPassMailQueue = "forgot-pass-mail-queue";
    @Bean
    Queue forgotPassMailQueue(){
        return new Queue(forgotPassMailQueue);
    }
}