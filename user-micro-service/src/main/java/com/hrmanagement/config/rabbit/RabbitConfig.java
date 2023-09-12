package com.hrmanagement.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchange="user-exchange";


    // Auth register consumer
    String authRegisterQueue = "user-register-queue";

    @Bean
    DirectExchange userExchange(){
        return new DirectExchange(exchange);
    }

    //Employee eklemek için producer

    private String authEmployeeaddQueue="auth-employeeadd-queue";
    private String authEmployeeaddBinding="auth-employeeadd-binding";

    @Bean
    Queue authRegisterQueue(){
        return new Queue(authRegisterQueue);
    }

    @Bean
    Queue authEmployeeaddQueue(){
        return new Queue(authEmployeeaddQueue);
    }
    @Bean
    public Binding authEmployeeaddBinding(final Queue authEmployeeaddQueue, final DirectExchange userExchange){
        return BindingBuilder
                .bind(authEmployeeaddQueue)
                .to(userExchange)
                .with(authEmployeeaddBinding);
    }




//    // Employee Kaydetme metodları
//    private String exchangeCreateEmployeeForAuth = "exchange-employee-auth";
//    private String keyEmployeeAuth = "key-employee-auth";
//    private String queueEmployeeAuth = "queue-employee-auth";
//
//    @Bean
//    DirectExchange directExchangeEmployeeAuth() {
//        return new DirectExchange(exchangeCreateEmployeeForAuth);
//    }
//    @Bean
//    Queue queueEmployeeAuth() {
//        return new Queue(queueEmployeeAuth);
//    }
//    @Bean
//    Binding bindingEmployeeAuth(DirectExchange directExchangeEmployeeAuth, Queue queueEmployeeAuth) {
//        return BindingBuilder.bind(queueEmployeeAuth).to(directExchangeEmployeeAuth).with(keyEmployeeAuth);
//    }

}
