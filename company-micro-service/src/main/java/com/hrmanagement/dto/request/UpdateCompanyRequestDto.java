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
public class UpdateCompanyRequestDto {

    String name;
    String taxNumber;
    String phone;
    String address;
    String email;
    Integer numberOfWorkers;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate yearOfEstablishment;

}
