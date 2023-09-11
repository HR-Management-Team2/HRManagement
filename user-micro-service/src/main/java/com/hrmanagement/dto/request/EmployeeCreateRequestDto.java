package com.hrmanagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeCreateRequestDto {
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String password;
    private String address;
    private String phone;
    private LocalDate birthday;
    private String birthdayPlace;
    private String company_name;
    private String occupation;
    private Double salary;

}
