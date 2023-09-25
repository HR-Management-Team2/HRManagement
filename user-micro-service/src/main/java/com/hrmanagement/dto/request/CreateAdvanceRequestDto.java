package com.hrmanagement.dto.request;
import com.hrmanagement.repository.enums.EAdvanceRequestType;
import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdvanceRequestDto {
    String token;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    LocalDate dateOfRequest;
    String description;
    LocalDate replyDate;
    Double advanceAmount;
    EAdvanceRequestType advanceRequestType;
    EApprovalStatus approvalStatus;
    ECurrency currency;
}
