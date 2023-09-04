package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequestDto {
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ERole role;
}
