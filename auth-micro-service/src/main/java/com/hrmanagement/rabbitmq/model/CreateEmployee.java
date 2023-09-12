package com.hrmanagement.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEmployee implements Serializable {

    String email;
    String companyName;
    String taxNo;

}