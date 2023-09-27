package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.EPermissionType;
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
public class PermissionResponseDto {
    String id;
    Long authId;
    String nameEmployee;
    String surnameEmployee;
    EPermissionType epermissionType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfRequest;
    EApprovalStatus approvalStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate replyDate;
    int days;
}
