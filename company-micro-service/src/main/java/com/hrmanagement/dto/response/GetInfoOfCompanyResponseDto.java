package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetInfoOfCompanyResponseDto {
    String id;
    String name;
    String taxNumber;
    String phone;
    String address;
    String email;
    Integer numberOfWorkers;
    LocalDate yearOfEstablishment;
    Status status;
}
