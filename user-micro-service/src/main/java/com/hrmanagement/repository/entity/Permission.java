package com.hrmanagement.repository.entity;

import com.hrmanagement.repository.enums.EApprovalStatus;
import com.hrmanagement.repository.enums.EPermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Document
public class Permission extends BaseEntity {
    @Id
    String id;
    Long authId;
    String nameEmployee;
    String surnameEmployee;
    EPermissionType ePermissionType;
    LocalDate dateOfRequest;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate replyDate;
    EApprovalStatus approvalStatus;
    String taxNo;
    String companyName;
}
