package com.hrmanagement.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateEmployee implements Serializable {
    String name;
    String surname;
    String email;
    String companyName;
    String taxNo;

}