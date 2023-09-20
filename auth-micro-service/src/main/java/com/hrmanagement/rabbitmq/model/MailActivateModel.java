package com.hrmanagement.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MailActivateModel implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
}
