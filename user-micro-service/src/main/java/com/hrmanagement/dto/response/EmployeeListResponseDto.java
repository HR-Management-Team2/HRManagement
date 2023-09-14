package com.hrmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListResponseDto {
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
    private String occupation;

}
