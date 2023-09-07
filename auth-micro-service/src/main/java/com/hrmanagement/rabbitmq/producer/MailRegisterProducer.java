package com.hrmanagement.rabbitmq.producer;

import com.hrmanagement.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailRegisterProducer {

    private String exchange = "auth-exchange";
    private String mailRegisterBinding = "mail-register-binding";

    private final RabbitTemplate rabbitTemplate;
    public void sendRegisterMail(MailRegisterModel mailRegisterModel){
        rabbitTemplate.convertAndSend(exchange, mailRegisterBinding, mailRegisterModel);
    }
}