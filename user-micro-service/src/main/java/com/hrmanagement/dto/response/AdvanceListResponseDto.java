package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EAdvanceRequestType;
import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvanceListResponseDto {
    EAdvanceRequestType advanceRequestType;
    String description;
    Double advanceAmount;
    ECurrency currency;
    LocalDate replyDate;
    EApprovalStatus approvalStatus;
}
