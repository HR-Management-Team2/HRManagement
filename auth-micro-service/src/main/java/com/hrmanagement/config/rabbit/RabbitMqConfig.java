package com.hrmanagement.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private String exchange = "auth-exchange"; // tektir, yani her producer işlemi için birden çok oluşturmaya gerek yok
    @Bean
    DirectExchange authExchange(){
        return new DirectExchange(exchange);
    }

    // User register producer
    private String userRegisterQueue = "user-register-queue";  // her producer işlemi için yeniden bir değişken oluşturulmalıdır
    private String userRegisterBinding = "user-register-binding"; // her producer işlemi için yeniden bir değişken oluşturulmalıdır

    @Bean
    Queue userRegisterQueue(){
        return new Queue(userRegisterQueue);
    }

    @Bean
    public Binding userRegisterBinding(final Queue userRegisterQueue, final DirectExchange authExchange){
        return BindingBuilder
                .bind(userRegisterQueue)
                .to(authExchange)
                .with(userRegisterBinding);
    }

    //Mail sender register producer
    private String mailRegisterQueue = "mail-register-queue";
    private String mailRegisterBinding = "mail-register-binding";

    @Bean
    Queue mailRegisterQueue(){
        return new Queue(mailRegisterQueue);
    }
    @Bean
    public Binding mailRegisterBinding(final Queue mailRegisterQueue, final DirectExchange authExchange){
        return BindingBuilder.bind(mailRegisterQueue).to(authExchange).with(mailRegisterBinding);
    }
    //Employee-added-consumer
    String userEmployeeaddQueue="auth-employeeadd-queue";
    @Bean
    Queue userEmployeeaddQueue() {
        return new Queue(userEmployeeaddQueue);
    }

//    //Empolyee kaydetme metodları
//    private String exchangeCreateEmployeeForAuth = "exchange-employee-auth";
//
//    private String keyEmployeeAuth = "key-employee-auth";
//
//    private String queueEmployeeAuth = "queue-employee-auth";
//
//    @Bean
//    DirectExchange directExchangeEmployeeAuth() {
//        return new DirectExchange(exchangeCreateEmployeeForAuth);
//    }
//
//    @Bean
//    Queue queueEmployeeAuth() {
//        return new Queue(queueEmployeeAuth);
//    }
//
//    @Bean
//    Binding bindingEmployeeAuth(DirectExchange directExchangeEmployeeAuth, Queue queueEmployeeAuth) {
//        return BindingBuilder.bind(queueEmployeeAuth).to(directExchangeEmployeeAuth).with(keyEmployeeAuth);
//    }

}
