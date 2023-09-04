package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCompanyRequestDto {

    String name;
    String taxNumber;
    String phone;
    String address;
    String email;
    Integer numberOfWorkers;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate yearOfEstablishment;

    Status status;
}
