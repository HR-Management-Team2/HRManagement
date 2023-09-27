package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.EPermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PermissionListManagerResponseDto {
    String id;
    String nameEmployee;
    String surnameEmployee;
    EPermissionType epermissionType;
    LocalDate dateOfRequest;
    LocalDate startDate;
    LocalDate endDate;
    EApprovalStatus approvalStatus;
    int days;
}
