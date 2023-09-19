package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManagerListResponseDto {

    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String companyName;
    private String taxNo;
    private EStatus status;
}
