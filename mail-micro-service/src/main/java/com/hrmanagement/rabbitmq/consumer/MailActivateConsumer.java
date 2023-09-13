package com.hrmanagement.rabbitmq.consumer;


import com.hrmanagement.rabbitmq.model.MailActivateModel;
import com.hrmanagement.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailActivateConsumer {
    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "mail-activate-queue")
    public void sendActivateUserInfo(MailActivateModel mailActivateModel){
        mailSenderService.sendActivateUserInfo(mailActivateModel);
    }


}
