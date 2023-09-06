package com.hrmanagement.rabbitmq.consumer;

import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRegisterConsumer {

    private final UserService userService;

    @RabbitListener(queues = "user-register-queue")
    public void newUserCreate(UserRegisterModel model){
        userService.createUser(model);
    }

}
