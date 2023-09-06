package com.hrmanagement.rabbitmq.model;

import com.hrmanagement.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRegisterModel implements Serializable {
    Long authId;
    String name;
    String surname;
    String email;
    String password;
    ERole role;
}
