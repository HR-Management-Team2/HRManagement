package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EAdvanceRequestType;
import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdvanceListManagerResponseDto {
    String id;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    EAdvanceRequestType advanceRequestType;
    String description;
    Double advanceAmount;
    ECurrency currency;
    LocalDate dateOfRequest;
    EApprovalStatus approvalStatus;
}
