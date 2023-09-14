package com.hrmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateAdminRequestDto {
    String name;
    String surname;
    String email;
    String password;
}
