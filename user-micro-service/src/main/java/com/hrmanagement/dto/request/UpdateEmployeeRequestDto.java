package com.hrmanagement.dto.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class UpdateEmployeeRequestDto {
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String address;
    private String phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String birthdayPlace;
    private String occupation;
    private Double salary;
}
