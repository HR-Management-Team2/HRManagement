package com.hrmanagement.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MailActivateModel {
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String password;
}
