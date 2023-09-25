package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.EApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateStatusRequestDto {
    String id;
    EApprovalStatus approvalStatus;
}
