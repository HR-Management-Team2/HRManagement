package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.EAdvanceRequestType;
import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import com.hrmanagement.repository.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateExpenseRequestDto {
    String token;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    LocalDate dateOfRequest;
    String description;
    LocalDate replyDate;
    Double expenseAmount;
    EExpenseType expenseType;
    EApprovalStatus approvalStatus;
    ECurrency currency;
}
