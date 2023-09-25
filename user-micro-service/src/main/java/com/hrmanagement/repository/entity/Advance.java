package com.hrmanagement.repository.entity;

import com.hrmanagement.repository.enums.EAdvanceRequestType;
import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Advance {
    @Id
    String id;
    Long authId;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    LocalDate dateOfRequest;
    String description;
    LocalDate replyDate;
    Double advanceAmount;
    EAdvanceRequestType advanceRequestType;
    EApprovalStatus approvalStatus;
    ECurrency currency;
    String taxNo;
    String companyName;

}
