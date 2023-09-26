package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import com.hrmanagement.repository.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDto {
    EExpenseType expenseType;
    String description;
    Double expenseAmount;
    ECurrency currency;
    LocalDate replyDate;
    EApprovalStatus approvalStatus;
}
