package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {

    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String phone;
    private LocalDate birthday;
    private String birthdayPlace;
    private String company_name;
    private Double salary;
    private LocalDate start_date;
    private LocalDate end_date;
    private EStatus status ;
    private ERole role;

}
