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
public class ExpenseManagerResponseDto {
    String id;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    EExpenseType expenseType;
    String description;
    Double expenseAmount;
    ECurrency currency;
    LocalDate dateOfRequest;
    EApprovalStatus approvalStatus;
}
