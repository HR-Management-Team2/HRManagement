package com.hrmanagement.rabbitmq.producer;

import com.hrmanagement.rabbitmq.model.CreateEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProducer {

    private String exchange="user-exchange";
    private String authEmployeeaddBinding="auth-employeeadd-binding";
    private final RabbitTemplate rabbitTemplate;

     //employe kaydetme ıcın send empoloyee  metodu
    public Long sendEmployeeAuth(CreateEmployee createEmployee){
        return  (Long) rabbitTemplate.convertSendAndReceive(exchange,authEmployeeaddBinding,createEmployee);
    }

}
