package com.hrmanagement.rabbitmq.producer;


import com.hrmanagement.rabbitmq.model.MailActivateModel;
import com.hrmanagement.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailActivateProducer {

    private String exchange = "auth-exchange";
    private String mailActivateBinding = "mail-activate-binding";

    private final RabbitTemplate rabbitTemplate;

    public void sendActivateMail(MailActivateModel mailActivateModel){
        rabbitTemplate.convertAndSend(exchange,mailActivateBinding, mailActivateModel);
    }

}
