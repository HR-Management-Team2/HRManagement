package com.hrmanagement.rabbitmq.consumer;

import com.hrmanagement.exception.AuthManagerException;
import com.hrmanagement.exception.ErrorType;
import com.hrmanagement.rabbitmq.model.CreateEmployee;
import com.hrmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "auth-employeeadd-queue")
    public Long createEmployee(CreateEmployee createEmployee){
        return authService.createEmployee(createEmployee);
    }
}
