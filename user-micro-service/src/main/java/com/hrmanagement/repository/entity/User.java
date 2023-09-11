package com.hrmanagement.repository.entity;

import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class User extends BaseEntity {
    @Id
    private String id;
    private Long authId;
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String password;
    private String address;
    private String phone;
    private LocalDate birthday;
    private String birthdayPlace;
    private String company_name;
    private String occupation;
    private Double salary;
    private LocalDate start_date;
    private LocalDate end_date;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
    private ERole role;



}
